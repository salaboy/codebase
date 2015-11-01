/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.services.endpoints.school.impl;

import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.codebase.core.school.api.SchoolsService;
import org.codebase.model.school.Course;
import org.codebase.model.school.School;
import org.codebase.model.school.SchoolClass;
import org.codebase.model.school.Year;
import org.codebase.model.school.users.Student;
import org.codebase.model.school.users.Teacher;
import org.codebase.services.endpoints.school.api.PublicSchoolEndpointService;
import org.codebase.shared.exceptions.ServiceException;

/**
 *
 * @author salaboy
 */
@ApplicationScoped
public class PublicSchoolEndpointServiceImpl implements PublicSchoolEndpointService {

    @Inject
    private SchoolsService schoolsService;
    
    private final static Logger log = Logger.getLogger(PublicSchoolEndpointServiceImpl.class.getName());

    public PublicSchoolEndpointServiceImpl() {

    }

    @Override
    public List<School> getAllSchools() throws ServiceException {
        return schoolsService.getAllSchools();    
    }

    @Override
    public Long newSchool(School school) throws ServiceException {
        return schoolsService.newSchool(school);
    }

    @Override
    public Long newTeacher(Teacher teacher) {
        return schoolsService.newTeacher(teacher);
    }

    @Override
    public Long newCourse(Course course) {
        return schoolsService.newCourse(course);
    }

    @Override
    public Long newYear(Year year) {
        return schoolsService.newYear(year);
    }

    @Override
    public Long newSchoolClass(SchoolClass schoolClass) {
        return schoolsService.newSchoolClass(schoolClass);
    }

    @Override
    public Long newStudent(Student student) {
        return schoolsService.newStudent(student);
    }

    @Override
    public void enrollStudentToSchoolClass(Long schoolClassId, Student student) {
        schoolsService.enrollStudentToSchoolClass(schoolClassId, student);
    }

    @Override
    public List<Student> getStudentsBySchoolClassCode(Long newSchoolId, String math) {
        return schoolsService.getStudentsBySchoolClassCode(newSchoolId, math);
    }

    @Override
    public List<SchoolClass> getSchoolClassByYear(Long newSchoolId, String string) {
        return schoolsService.getSchoolClassByYear(newSchoolId, string);
    }

    @Override
    public List<Year> getYearsBySchool(Long newSchoolId) {
        return schoolsService.getYearsBySchool(newSchoolId);
    }

    @Override
    public List<Course> getCoursesBySchool(Long newSchoolId) {
        return schoolsService.getCoursesBySchool(newSchoolId);
    }
    
    @Override
    public School getSchool(Long schoolId) {
        return schoolsService.getSchool(schoolId);
    }

    @Override
    public void updateSchool(School school) throws ServiceException {
        schoolsService.updateSchool(school);
    }
    
    
    

}
