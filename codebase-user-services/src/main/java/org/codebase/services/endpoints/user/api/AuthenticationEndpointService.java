/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.services.endpoints.user.api;

import java.io.Serializable;
import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codebase.core.exceptions.ServiceException;
import org.codebase.services.util.Payload;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;



/**
 *
 * @author salaboy
 */

@Path("/auth")
@Local
public interface AuthenticationEndpointService extends Serializable {

    
    
    @POST
    @Path("/create")
    @Produces({MediaType.APPLICATION_JSON})
    public Response createUser(@NotEmpty @FormParam("firstname") String firstname, 
            @NotEmpty  @FormParam("lastname") String lastname, 
            @NotNull @Email @NotEmpty @FormParam("email") String email,
            @NotNull @NotEmpty @FormParam("password") String password, 
            @NotNull @NotEmpty @FormParam("gender") String gender, @FormParam("birthday") String birthday) throws ServiceException;

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(
            @Context HttpHeaders httpHeaders,
            @NotNull @Email @NotEmpty @FormParam("email") String email,
            @NotNull @NotEmpty @FormParam("password") String password) throws ServiceException;
    


    @POST
    @Path("/logout")
    public Response logout(
            @Context HttpHeaders httpHeaders) throws ServiceException;


}
