/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.services.endpoints.user.api;

import java.io.Serializable;
import javax.ejb.Local;
import javax.validation.constraints.NotNull;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codebase.core.exceptions.ServiceException;
import org.codebase.model.user.User;



/**
 *
 * @author salaboy
 */

@Path("/auth")
@Local
public interface AuthenticationEndpointService extends Serializable {

    @POST
    @Path("/new")
    @Produces({MediaType.APPLICATION_JSON})
    public Response createUser(@NotNull User user) throws ServiceException;

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@NotNull User user) throws ServiceException;

    @POST
    @Path("/logout")
    public Response logout(
            @Context HttpHeaders httpHeaders) throws ServiceException;


}
