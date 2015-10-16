/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.model.school.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import org.codebase.model.user.User;
import org.hibernate.search.annotations.Indexed;

/**
 *
 * @author salaboy
 */
@Entity
@Indexed
@DiscriminatorValue(value = "Teacher")
public class Teacher extends User {
 
    private String teacherDescription;

  
    
    
    public Teacher() {
    }

    public Teacher(String email, String password) {
        super(email, password);
    }

    public String getTeacherDescription() {
        return teacherDescription;
    }

    public void setTeacherDescription(String teacherDescription) {
        this.teacherDescription = teacherDescription;
    }

   

   

}
