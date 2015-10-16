/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.services.endpoints.school.impl;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.Response;

import org.codebase.core.exceptions.ServiceException;
import org.codebase.core.school.api.SchoolsService;
import org.codebase.model.school.School;
import org.codebase.services.endpoints.school.api.PublicSchoolEndpointService;
import static org.codebase.services.endpoints.school.impl.SchoolsHelper.createJsonSchool;

/**
 *
 * @author salaboy
 */
@Stateless
public class PublicSchoolEndpointServiceImpl implements PublicSchoolEndpointService {

    @Inject
    private SchoolsService schoolsService;
    
    private final static Logger log = Logger.getLogger(PublicSchoolEndpointServiceImpl.class.getName());

    public PublicSchoolEndpointServiceImpl() {

    }

   

    @Override
    public Response getAll() throws ServiceException {
        List<School> schools = schoolsService.getAllSchools();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (School s : schools) {

            JsonObjectBuilder jsonObjBuilder = createJsonSchool(s);
            jsonArrayBuilder.add(jsonObjBuilder);

        }
        return Response.ok(jsonArrayBuilder.build().toString()).build();
    }

   

}
