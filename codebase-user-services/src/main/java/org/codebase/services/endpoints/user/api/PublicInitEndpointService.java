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
import javax.ws.rs.core.Response;
import org.codebase.shared.exceptions.ServiceException;

/**
 *
 * @author salaboy
 */

@Path("/public/app")
@Local
public interface PublicInitEndpointService extends Serializable {

    @GET
    @Path("/createusers")
    Response initApplication() throws ServiceException;
    
    @GET
    @Path("/userscreated")
    Response usersCreated() throws ServiceException;


}
