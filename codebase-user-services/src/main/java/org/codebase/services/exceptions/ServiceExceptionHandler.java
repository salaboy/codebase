/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.services.exceptions;

import org.codebase.core.exceptions.ServiceException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author salaboy
 */
@Provider
public class ServiceExceptionHandler implements ExceptionMapper<ServiceException> {

    public Response toResponse(ServiceException exception) {
        return Response.status((exception.isIsSecurityRelated())?Status.UNAUTHORIZED:Status.BAD_REQUEST)
                .entity(new ErrorMessage(exception.getMessage(), exception.isIsSecurityRelated())
                ).type(MediaType.APPLICATION_JSON).build();

    }
    
    

}
