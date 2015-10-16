/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.services.endpoints.user.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.codebase.model.user.User;
import org.codebase.services.endpoints.user.api.AuthenticationEndpointService;
import org.codebase.core.exceptions.ServiceException;
import org.codebase.services.filters.auth.GrogAuthenticator;
import org.codebase.services.filters.auth.GrogHTTPHeaderNames;
import org.codebase.core.user.api.UsersService;


/**
 *
 * @author salaboy
 */
@Stateless
public class AuthenticationEndpointServiceImpl implements AuthenticationEndpointService {

    @Inject
    private UsersService userService;

    @Inject
    private GrogAuthenticator authenticator;

    private final static Logger log = Logger.getLogger(AuthenticationEndpointServiceImpl.class.getName());



    public AuthenticationEndpointServiceImpl() {

    }

    

    @Override
    public Response createUser(User user) throws ServiceException {
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date result = null;
        try {
            result = df.parse(user.getBirthday().toString());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        user.setBirthday(result);
        userService.newUser(user);
        return Response.ok().build();
    }

    @Override
    public Response login(User user) throws ServiceException {

        User authUser = userService.getByEmail(user.getEmail());

        if (authUser == null) {
            return getNoCacheResponseBuilder(Response.Status.UNAUTHORIZED).build();
        }

        String authToken = authenticator.login(authUser.getEmail(), authUser.getPassword());

        boolean firstLogin = authUser.isIsFirstLogin();
        boolean live = authUser.isLive();
        JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
        jsonObjBuilder.add("email", authUser.getEmail());
        jsonObjBuilder.add("auth_token", authToken);
        jsonObjBuilder.add("user_id", authUser.getId());
        jsonObjBuilder.add("firstLogin", firstLogin);
        jsonObjBuilder.add("live", live);

        JsonObject jsonObj = jsonObjBuilder.build();

        return getNoCacheResponseBuilder(Response.Status.OK).entity(jsonObj.toString()).build();

    }

    @Override
    public Response logout(
            @Context HttpHeaders httpHeaders) throws ServiceException {

        String serviceKey = httpHeaders.getHeaderString(GrogHTTPHeaderNames.SERVICE_KEY);

        String authToken = httpHeaders.getHeaderString(GrogHTTPHeaderNames.AUTH_TOKEN);

        authenticator.logout(serviceKey, authToken);

        return getNoCacheResponseBuilder(Response.Status.NO_CONTENT).build();

    }

    private Response.ResponseBuilder getNoCacheResponseBuilder(Response.Status status) {

        CacheControl cc = new CacheControl();

        cc.setNoCache(true);

        cc.setMaxAge(-1);

        cc.setMustRevalidate(true);

        return Response.status(status).cacheControl(cc);

    }

    public UsersService getUserService() {
        return userService;
    }

    public void setUserService(UsersService userService) {
        this.userService = userService;
    }

    public GrogAuthenticator getAuthenticator() {
        return authenticator;
    }

    public void setAuthenticator(GrogAuthenticator authenticator) {
        this.authenticator = authenticator;
    }

    

    

}
