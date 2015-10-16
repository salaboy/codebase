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
@DiscriminatorValue(value = "Student")
public class Student extends User {

    private String notes;

    public Student() {
    }

    public Student(String email, String password) {
        super(email, password);
    }

    
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
