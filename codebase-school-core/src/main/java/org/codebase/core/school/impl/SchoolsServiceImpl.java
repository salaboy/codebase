/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tpmplate file, choose Tools | Tpmplates
 * and open the tpmplate in the editor.
 */
package org.codebase.core.school.impl;

import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.codebase.core.exceptions.ServiceException;
import org.codebase.core.util.PersistenceManager;
import org.codebase.core.school.api.SchoolsService;
import org.codebase.model.school.Course;
import org.codebase.model.school.School;
import org.codebase.model.school.SchoolClass;
import org.codebase.model.school.Year;
import org.codebase.model.school.users.Student;
import org.codebase.model.school.users.Teacher;

/**
 *
 * @author salaboy
 */
@ApplicationScoped
public class SchoolsServiceImpl implements SchoolsService {

    @Inject
    private PersistenceManager pm;

    private final static Logger log = Logger.getLogger(SchoolsServiceImpl.class.getName());

    public SchoolsServiceImpl() {
    }

    @Override
    public Long newSchool(School school) throws ServiceException {
      
        pm.persist(school);

        return school.getId();
    }

    @Override
    public Long newTeacher(Teacher teacher) {
        pm.persist(teacher);
        return teacher.getId();
    }

    @Override
    public Long newCourse(Course course) {
        pm.persist(course);
        return course.getId();
    }

    @Override
    public Long newYear(Year year) {
        pm.persist(year);
        return year.getId();
    }

    @Override
    public Long newSchoolClass(SchoolClass schoolClass) {
        pm.persist(schoolClass);
        return schoolClass.getId();
    }
    
    @Override
    public void enrollStudentToSchoolClass(Long schoolClassId, Student student) {
        SchoolClass find = pm.find(SchoolClass.class, schoolClassId);
        find.enrollStudent(student);
        pm.merge(find);
    }

    @Override
    public Long newStudent(Student student) {
        pm.persist(student);
        return student.getId();
    }

    @Override
    public List<Student> getStudentsBySchoolClassCode(Long schoolId, String classCode) {
       return (List<Student>) pm.createNamedQuery("ScoolClass.getStudents").setParameter("schoolId", schoolId)
                                                    .setParameter("classCode", classCode).getResultList();
        
    }

    @Override
    public List<SchoolClass> getSchoolClassByYear(Long schoolId, String yearName) {
      return (List<SchoolClass>) pm.createNamedQuery("SchoolClass.getByYear").setParameter("schoolId", schoolId)
              .setParameter("yearName", yearName).getResultList();
    }

    @Override
    public List<Year> getYearsBySchool(Long schoolId) {
        return (List<Year>) pm.createNamedQuery("Year.getBySchool").setParameter("schoolId", schoolId).getResultList();
    }

    @Override
    public List<Course> getCoursesBySchool(Long schoolId) {
       return (List<Course>) pm.createNamedQuery("Course.getBySchool").setParameter("schoolId", schoolId).getResultList();
    }

    @Override
    public List<School> getAllSchools() {
        return pm.createNamedQuery("School.getAll", School.class).getResultList();
    }

    
    
    
    
   

}
