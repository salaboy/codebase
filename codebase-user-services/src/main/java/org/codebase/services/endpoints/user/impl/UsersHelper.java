/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.services.endpoints.user.impl;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import org.codebase.model.user.User;

/**
 *
 * @author salaboy
 */
public class UsersHelper {
    public static JsonObjectBuilder createFullJsonUser(User u) {
        JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
        jsonObjBuilder.add("userId", (u.getId() == null) ? "" : u.getId().toString());
        jsonObjBuilder.add("bio", (u.getBio() == null) ? "" : u.getBio());
        jsonObjBuilder.add("firstname", (u.getFirstname() == null) ? "" : u.getFirstname());
        jsonObjBuilder.add("lastname", (u.getLastname() == null) ? "" : u.getLastname());
        jsonObjBuilder.add("gender", (u.getGender() == null) ? "" : u.getGender());
        jsonObjBuilder.add("birthday", (u.getBirthday()== null) ? "" :  String.valueOf(u.getBirthday().getTime()));
        jsonObjBuilder.add("live", u.isLive());
        
        return jsonObjBuilder;
    }
    
    
   
}
