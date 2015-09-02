/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.services.endpoints.user.api;

import java.io.Serializable;
import javax.ejb.Local;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codebase.core.exceptions.ServiceException;

/**
 *
 * @author salaboy
 */
@Path("/users")
@Local
public interface UserEndpointService extends Serializable {

    @GET
    @Path("")
    @Produces({MediaType.APPLICATION_JSON})
    Response getAll() throws ServiceException;

    @GET
    @Path("/search")
    @Produces({MediaType.APPLICATION_JSON})
    Response search(
            @QueryParam("offset") Integer offset,
            @QueryParam("limit") Integer limit,
            @QueryParam("excludes") String excludes) throws ServiceException;

    @Path("{id}/exist")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    Response exist(@NotNull @PathParam("id") Long user_id) throws ServiceException;

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    Response get(@PathParam("id") Long user_id) throws ServiceException;

    @Path("{id}/firstlogin")
    @PUT
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Response updateFirstLogin(@NotNull @PathParam("id") Long user_id) throws ServiceException;

    @Path("{id}/live")
    @PUT
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Response updateLive(@NotNull @PathParam("id") Long user_id, @FormParam("live") String live) throws ServiceException;

    @Path("{id}/firstname")
    @PUT
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Response updateFirstName(@NotNull @PathParam("id") Long user_id, @FormParam("firstname") String firstname) throws ServiceException;

    @Path("{id}/bothnames")
    @PUT
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Response updateBothNames(@NotNull @PathParam("id") Long user_id, @FormParam("firstname") String firstname,
            @FormParam("lastname") String lastname) throws ServiceException;

    @Path("{id}/lastname")
    @PUT
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Response updateLastName(@NotNull @PathParam("id") Long user_id,
            @FormParam("lastname") String lastname) throws ServiceException;

    @Path("{id}/bio")
    @PUT
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Response updateBio(@NotNull @PathParam("id") Long user_id, @FormParam("bio") String bio) throws ServiceException;

}
