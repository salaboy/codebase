/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.core.school.api;

import java.util.List;
import org.codebase.model.school.Course;
import org.codebase.model.school.School;
import org.codebase.model.school.SchoolClass;
import org.codebase.model.school.Year;
import org.codebase.model.school.users.Student;
import org.codebase.model.school.users.Teacher;
import org.codebase.shared.exceptions.ServiceException;

/**
 *
 * @author salaboy
 */
public interface SchoolsService {

    public Long newSchool(School school) throws ServiceException;
    
    public void updateSchool(School school) throws ServiceException;

    public Long newTeacher(Teacher teacher);

    public Long newCourse(Course course);

    public Long newYear(Year year);

    public Long newSchoolClass(SchoolClass schoolClass);

    public Long newStudent(Student student);
    
    void enrollStudentToSchoolClass(Long schoolClassId, Student student);

    public List<Student> getStudentsBySchoolClassCode(Long newSchoolId, String math);

    public List<SchoolClass> getSchoolClassByYear(Long newSchoolId, String string);

    public List<Year> getYearsBySchool(Long newSchoolId);

    public List<Course> getCoursesBySchool(Long newSchoolId);

    public List<School> getAllSchools();
    
    public School getSchool(Long schoolId);

    public void deleteSchool(Long schoolId);

    public void updateCourse(Long courseId, Course course);

    public void deleteCourse(Long courseId);

    public Course getCourse(Long courseId);

    public void deleteStudent(Long studentId);

    public List<Student> getAllStudents();

    public Student getStudent(Long studentId);

    public void updateStudent(Long studentId, Student student);

    public Teacher getTeacher(Long teacherId);

    public List<Teacher> getAllTeachers();

    public void deleteTeacher(Long teacherId);

    public void updateTeacher(Long teacherId, Teacher teacher);

}
