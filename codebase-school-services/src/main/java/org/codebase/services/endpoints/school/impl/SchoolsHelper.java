/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.services.endpoints.school.impl;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import org.codebase.model.school.School;

/**
 *
 * @author salaboy
 */
public class SchoolsHelper {
    public static JsonObjectBuilder createJsonSchool(School s) {
        JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
        jsonObjBuilder.add("schoolId", (s.getId() == null) ? "" : s.getId().toString());
        jsonObjBuilder.add("email", (s.getContactEmail() == null) ? "" : s.getContactEmail());
        jsonObjBuilder.add("description", (s.getDescription() == null) ? "" : s.getDescription());
        jsonObjBuilder.add("lat", (s.getLatitude() == null) ? "" : s.getLatitude().toString());
        jsonObjBuilder.add("lon", (s.getLongitude()== null) ? "" : s.getLongitude().toString());
        jsonObjBuilder.add("name", (s.getSchoolName()== null) ? "" :  s.getSchoolName());
        jsonObjBuilder.add("website", (s.getWebsite()== null) ? "" :  s.getWebsite());
        
        return jsonObjBuilder;
    }
    
    
   
}
