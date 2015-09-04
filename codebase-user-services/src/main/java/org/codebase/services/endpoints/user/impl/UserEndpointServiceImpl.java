/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.services.endpoints.user.impl;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.codebase.model.user.User;

import org.codebase.core.user.api.UsersService;

import org.codebase.services.endpoints.user.api.UserEndpointService;
import static org.codebase.services.endpoints.user.impl.UsersHelper.createFullJsonUser;
import org.codebase.core.exceptions.ServiceException;
import org.codebase.core.user.api.UsersQueryService;

/**
 *
 * @author salaboy
 */
@Stateless
public class UserEndpointServiceImpl implements UserEndpointService {

    @Inject
    private UsersService usersService;

    @Inject
    private UsersQueryService usersQueryService;

    private final static Logger log = Logger.getLogger(UserEndpointServiceImpl.class.getName());

    public UserEndpointServiceImpl() {

    }

    @Override
    public Response get(@PathParam("id") Long user_id) throws ServiceException {
        User u = usersService.getById(user_id);
        if (u == null) {
            throw new ServiceException("User " + user_id + " doesn't exists");
        }
        JsonObjectBuilder jsonUserObjectBuilder = createFullJsonUser(u);
        JsonObject jsonObj = jsonUserObjectBuilder.build();
        return Response.ok(jsonObj.toString()).build();
    }

    @Override
    public Response getAll() throws ServiceException {
        List<User> users = usersService.getAllLive();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (User u : users) {
            JsonObjectBuilder jsonUserObjectBuilder = createFullJsonUser(u);
            jsonArrayBuilder.add(jsonUserObjectBuilder);
        }
        return Response.ok(jsonArrayBuilder.build().toString()).build();
    }

    @Override
    public Response search(Integer offset, Integer limit, String excludesEmail) throws ServiceException {

        List<String> excludesList = null;
        if (excludesEmail != null) {
            JsonReader reader = Json.createReader(new ByteArrayInputStream(excludesEmail.getBytes()));
            JsonArray array = reader.readArray();
            reader.close();

            if (array != null) {
                excludesList = new ArrayList<String>(array.size());
                for (int i = 0; i < array.size(); i++) {
                    log.info("Excludes[" + i + "]: " + array.getString(i));

                    excludesList.add(array.getString(i));
                }

            }
        }

        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        List<User> users = usersQueryService.search(offset, limit, excludesList);
        int i = offset;
        for (User u : users) {
            JsonObjectBuilder jsonUserObjectBuilder = createFullJsonUser(u);
            jsonUserObjectBuilder.add("offset", i);
            jsonArrayBuilder.add(jsonUserObjectBuilder);
            i++;

        }

        return Response.ok(jsonArrayBuilder.build().toString()).build();
    }

    @Override
    public Response exist(@NotNull @FormParam("user_id") Long user_id) throws ServiceException {
        return Response.ok(usersService.exist(user_id)).build();
    }

    @Override
    public Response updateFirstLogin(Long user_id) throws ServiceException {
        usersService.updateFirstLogin(user_id);
        return Response.ok().build();
    }

    @Override
    public Response updateFirstName(Long user_id, String firstname) throws ServiceException {
        usersService.updateFirstName(user_id, firstname);
        return Response.ok().build();
    }

    @Override
    public Response updateLastName(Long user_id, String lastname) throws ServiceException {
        usersService.updateLastName(user_id, lastname);
        return Response.ok().build();
    }

    @Override
    public Response updateBio(Long user_id, String bio) throws ServiceException {
        usersService.updateBio(user_id, bio);
        return Response.ok().build();
    }

    @Override
    public Response updateBothNames(Long user_id, String firstname, String lastname) throws ServiceException {
        usersService.updateBothNames(user_id, firstname, lastname);
        return Response.ok().build();
    }

    @Override
    public Response updateLive(Long user_id, String live) throws ServiceException {
        usersService.updateLive(user_id, live);
        return Response.ok().build();
    }

    @Override
    public Response updatePassword(Long user_id, String oldPassword, String newPassword) throws ServiceException {
        usersService.updatePassword(user_id, oldPassword, newPassword);
        return Response.ok().build();
    }
    
    

}
