/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.services.exceptions;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author salaboy
 */
@Provider
public class ConstraintValidationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    public Response toResponse(ConstraintViolationException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorMessage(exception.getMessage())
                ).type(MediaType.APPLICATION_JSON).build();
    }

}
