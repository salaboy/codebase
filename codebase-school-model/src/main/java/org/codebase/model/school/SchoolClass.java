/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.model.school;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.codebase.model.school.users.Student;
import org.codebase.model.school.users.Teacher;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author salaboy
 */
@Entity(name = "SchoolClass")
@Table(name = "SchoolClass")
@XmlRootElement
public class SchoolClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @NotNull
    private String code;

    @NotNull
    @ManyToOne
    private Course course;

    @NotNull
    @ManyToOne
    private Year year;

    @OneToMany
    private List<Student> students;

    @ManyToOne
    @NotNull
    private Teacher teacher;

    public SchoolClass() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void enrollStudent(Student student) {
        if (this.students == null) {
            this.students = new ArrayList<Student>();
        }
        this.students.add(student);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "SchoolClass{" + "id=" + id + ", code=" + code + ", course=" + course + ", year=" + year + ", students=" + students + ", teacher=" + teacher + '}';
    }

}
