/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.services.endpoints.user.impl;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.Response;
import org.codebase.model.user.User;
import org.codebase.services.endpoints.user.api.PublicUserEndpointService;
import static org.codebase.services.endpoints.user.impl.UsersHelper.createFullJsonUser;
import org.codebase.core.user.api.UsersService;
import org.codebase.shared.exceptions.ServiceException;

/**
 *
 * @author salaboy
 */
@Stateless
public class PublicUserEndpointServiceImpl implements PublicUserEndpointService {

    @Inject
    private UsersService usersService;

    private static String serverUrl;
    
    private String serverContext;

    private final static Logger log = Logger.getLogger(PublicUserEndpointServiceImpl.class.getName());

    public PublicUserEndpointServiceImpl() {

    }

    private String getServerUrl() {
        serverContext = System.getProperty("SERVERCONTEXT");
        serverUrl = System.getProperty("SERVERURL");
        if (serverUrl == null || serverUrl.equals("")) {
            serverUrl = "http://localhost:8080/";
        }
        serverUrl = serverUrl + serverContext;
        
        return serverUrl;
    }

    @Override
    public Response getAll() throws ServiceException {
        List<User> users = usersService.getAll();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (User u : users) {

            JsonObjectBuilder jsonObjBuilder = createFullJsonUser(u);
            jsonArrayBuilder.add(jsonObjBuilder);

        }
        return Response.ok(jsonArrayBuilder.build().toString()).build();
    }

   

}
