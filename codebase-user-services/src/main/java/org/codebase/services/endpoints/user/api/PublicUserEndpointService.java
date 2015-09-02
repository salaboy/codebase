/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.services.endpoints.user.api;

import java.io.Serializable;
import javax.ejb.Local;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codebase.core.exceptions.ServiceException;

/**
 *
 * @author salaboy
 */

@Path("/public/users")
@Local
public interface PublicUserEndpointService extends Serializable {

    @GET
    @Path("")
    @Produces({MediaType.APPLICATION_JSON})
    Response getAll() throws ServiceException;
    
    
    
  

}
