package org.codebase.model.school;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.codebase.model.school.users.Teacher;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author salaboy
 */
@Entity(name = "Course")
@Table(name = "Course")
@XmlRootElement
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    
    @NotEmpty
    @NotNull
    private String subject;

    @NotNull
    @ManyToOne
    private School school;
   
    
    
    public Course() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }


    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", subject=" + subject + ", school=" + school + '}';
    }

}
