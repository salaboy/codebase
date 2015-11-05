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
    public Long newTeacher(Teacher teacher) throws ServiceException {
        return schoolsService.newTeacher(teacher);
    }

    @Override
    public Long newYear(Year year) throws ServiceException {
        return schoolsService.newYear(year);
    }

    @Override
    public Long newSchoolClass(SchoolClass schoolClass) throws ServiceException{
        return schoolsService.newSchoolClass(schoolClass);
    }

    @Override
    public Long newStudent(Student student) throws ServiceException {
        return schoolsService.newStudent(student);
    }

    @Override
    public void enrollStudentToSchoolClass(Long schoolClassId, Student student) throws ServiceException {
        schoolsService.enrollStudentToSchoolClass(schoolClassId, student);
    }

    @Override
    public List<Student> getStudentsBySchoolClassCode(Long newSchoolId, String math) throws ServiceException {
        return schoolsService.getStudentsBySchoolClassCode(newSchoolId, math);
    }

    @Override
    public List<SchoolClass> getSchoolClassByYear(Long newSchoolId, String string) throws ServiceException {
        return schoolsService.getSchoolClassByYear(newSchoolId, string);
    }

    @Override
    public List<Year> getYearsBySchool(Long newSchoolId) throws ServiceException {
        return schoolsService.getYearsBySchool(newSchoolId);
    }

    @Override
    public List<Course> getCoursesBySchool(Long newSchoolId) throws ServiceException {
        return schoolsService.getCoursesBySchool(newSchoolId);
    }

    @Override
    public School getSchool(Long schoolId) throws ServiceException {
        return schoolsService.getSchool(schoolId);
    }

    @Override
    public void updateSchool(School school) throws ServiceException {
        schoolsService.updateSchool(school);
    }

    @Override
    public void deleteSchool(Long schoolId) throws ServiceException {
        schoolsService.deleteSchool(schoolId);
    }

    @Override
    public void updateCourse(Long courseId, Course course) throws ServiceException {
        schoolsService.updateCourse(courseId, course);
    }

    @Override
    public void deleteCourse(Long courseId) throws ServiceException {
        schoolsService.deleteCourse(courseId);
    }

    @Override
    public Long newCourse(Course course) throws ServiceException {
        return schoolsService.newCourse(course);
    }

    @Override
    public Course getCourse(Long courseId) throws ServiceException{
        return schoolsService.getCourse(courseId);
    }

    @Override
    public void deleteStudent(Long studentId) throws ServiceException {
        schoolsService.deleteStudent(studentId);
    }

    @Override
    public Student getStudent(Long studentId) throws ServiceException{
        return schoolsService.getStudent(studentId);
    }

    @Override
    public List<Student> getAllStudents() throws ServiceException{
        return schoolsService.getAllStudents();
    }

    @Override
    public void updateStudent(Long studentId, Student student) throws ServiceException {
        schoolsService.updateStudent(studentId, student);
    }

    @Override
    public Teacher getTeacher(Long teacherId) throws ServiceException {
        return schoolsService.getTeacher(teacherId);
    }

    @Override
    public List<Teacher> getAllTeachers() throws ServiceException {
        return schoolsService.getAllTeachers();
    }

    @Override
    public void deleteTeacher(Long teacherId) throws ServiceException {
        schoolsService.deleteTeacher(teacherId);
    }

    @Override
    public void updateTeacher(Long teacherId, Teacher teacher) throws ServiceException {
        schoolsService.updateTeacher(teacherId, teacher);
    }
    
    

}
