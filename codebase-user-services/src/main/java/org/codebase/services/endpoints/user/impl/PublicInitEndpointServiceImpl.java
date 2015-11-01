/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.services.endpoints.user.impl;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.codebase.model.user.User;

import org.codebase.services.endpoints.user.api.PublicInitEndpointService;
import org.codebase.core.user.api.UsersService;
import org.codebase.shared.exceptions.ServiceException;

//http://localhost:8080/codebase-server/rest/public/app/init
@Stateless
public class PublicInitEndpointServiceImpl implements PublicInitEndpointService {

    @Inject
    private UsersService usersService;
    //rhc env set JAVA_OPTS_EXT=-DSERVERURL="http://xxx.rhcloud.com/" -a xxx
    private String serverUrl;
    private String serverContext;

    private static boolean mockUsersCreated = false;

    public PublicInitEndpointServiceImpl() {

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

    public Response usersCreated() throws ServiceException {
        return Response.ok(mockUsersCreated).build();
    }

    public Response initApplication() throws ServiceException {
        try {

            DateFormat df = new SimpleDateFormat("EEE, MMM d, yyyy");

            createAdminUser("salaboy@gmail.com", "asdasd", "Salaboy", "AP", "male", df.format(new Date(System.currentTimeMillis())), "grog dj bio");
            createAdminUser("eze@gmail.com", "123123", "Eze", "Salatino", "male", df.format(new Date(System.currentTimeMillis())), "eze  bio");

            mockUsersCreated = true;

        } catch (Exception ex) {
            Logger.getLogger(PublicInitEndpointServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.ok().build();
    }

    private Long createAdminUser(String email, String password, String firstname, String lastname, String gender,
            String birthday, String bio) throws ServiceException {
         DateFormat df = new SimpleDateFormat("EEE, MMM d, yyyy");
        User user = new User(email, password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setGender(gender);
        try {
            user.setBirthday(df.parse(birthday));
        } catch (ParseException ex) {
            throw new ServiceException("Wrong Birthday format!");
        }
        user.setBio(bio);
        user.setLive(true);
        user.setIsFirstLogin(false);
        List<String> roles = new ArrayList<String>();
        roles.add("Admin");
        user.setRoles(roles);

        Long userId = usersService.newUser(user);

        return userId;
    }

}
