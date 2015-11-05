/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tpmplate file, choose Tools | Tpmplates
 * and open the tpmplate in the editor.
 */
package org.codebase.core.school.impl;

import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.codebase.core.school.api.SchoolsService;
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
@ApplicationScoped
public class SchoolsServiceImpl implements SchoolsService {

    @PersistenceContext
    private EntityManager em;

    private final static Logger log = Logger.getLogger(SchoolsServiceImpl.class.getName());

    public SchoolsServiceImpl() {
    }

    @Override
    @Transactional
    public Long newSchool(School school) throws ServiceException {
        em.persist(school);
        return school.getId();
    }

    @Override
    @Transactional
    public Long newTeacher(Teacher teacher) {
        em.persist(teacher);
        return teacher.getId();
    }

    @Override
    @Transactional
    public Long newCourse(Course course) {
        em.persist(course);
        return course.getId();
    }

    @Override
    @Transactional
    public Long newYear(Year year) {
        em.persist(year);
        return year.getId();
    }

    @Override
    @Transactional
    public Long newSchoolClass(SchoolClass schoolClass) {
        em.persist(schoolClass);
        return schoolClass.getId();
    }

    @Override
    @Transactional
    public void enrollStudentToSchoolClass(Long schoolClassId, Student student) {
        SchoolClass find = em.find(SchoolClass.class, schoolClassId);
        find.enrollStudent(student);
        em.merge(find);
    }

    @Override
    @Transactional
    public Long newStudent(Student student) {
        em.persist(student);
        return student.getId();
    }

    @Override
    public School getSchool(Long schoolId) {
        return em.find(School.class, schoolId);
    }

    @Override
    public List<Student> getStudentsBySchoolClassCode(Long schoolId, String classCode) {
        return (List<Student>) em.createNamedQuery("ScoolClass.getStudents").setParameter("schoolId", schoolId)
                .setParameter("classCode", classCode).getResultList();

    }

    @Override
    public List<SchoolClass> getSchoolClassByYear(Long schoolId, String yearName) {
        return (List<SchoolClass>) em.createNamedQuery("SchoolClass.getByYear").setParameter("schoolId", schoolId)
                .setParameter("yearName", yearName).getResultList();
    }

    @Override
    public List<Year> getYearsBySchool(Long schoolId) {
        return (List<Year>) em.createNamedQuery("Year.getBySchool").setParameter("schoolId", schoolId).getResultList();
    }

    @Override
    public List<Course> getCoursesBySchool(Long schoolId) {
        return (List<Course>) em.createNamedQuery("Course.getBySchool").setParameter("schoolId", schoolId).getResultList();
    }

    @Override
    public List<School> getAllSchools() {
        return em.createNamedQuery("School.getAll", School.class).getResultList();
    }

    @Override
    @Transactional
    public void updateSchool(School school) throws ServiceException {
        School schoolById = em.find(School.class, school.getId());
        schoolById.setContactEmail(school.getContactEmail());
        schoolById.setDescription(school.getDescription());
        schoolById.setSchoolName(school.getSchoolName());
        schoolById.setWebsite(school.getWebsite());
        em.merge(schoolById);
    }

    @Override
    @Transactional
    public void deleteSchool(Long schoolId) {
        School schoolById = em.find(School.class, schoolId);
        em.remove(schoolById);
    }

    @Override
    @Transactional
    public void updateCourse(Long courseId, Course course) {
        Course courseById = em.find(Course.class, courseId);
        courseById.setSubject(course.getSubject());
        School schoolById = em.find(School.class, course.getSchool().getId());
        courseById.setSchool(schoolById);
        em.merge(courseById);
    }

    @Override
    @Transactional
    public void deleteCourse(Long courseId) {
        Course courseById = em.find(Course.class, courseId);
        em.remove(courseById);
    }

    @Override
    public Course getCourse(Long courseId) {
        return em.find(Course.class, courseId);
    }

    @Override
    @Transactional
    public void deleteStudent(Long studentId) {
        Student studentById = em.find(Student.class, studentId);
        em.remove(studentById);
    }

    @Override
    public List<Student> getAllStudents() {
        return (List<Student>) em.createNamedQuery("Student.getAll").getResultList();
    }

    @Override
    public Student getStudent(Long studentId) {
        return em.find(Student.class, studentId);
    }

    @Override
    @Transactional
    public void updateStudent(Long studentId, Student student) {
        Student studentById = em.find(Student.class, studentId);
        studentById.setFirstname(student.getFirstname());
        studentById.setLastname(student.getLastname());
        studentById.setEmail(student.getEmail());
        studentById.setPassword(student.getPassword());
        studentById.setBio(student.getBio());
        studentById.setBirthday(student.getBirthday());
        em.merge(studentById);
    }

    @Override
    public Teacher getTeacher(Long teacherId) {
        return em.find(Teacher.class, teacherId);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return (List<Teacher>) em.createNamedQuery("Teacher.getAll").getResultList();
    }

    @Override
    @Transactional
    public void deleteTeacher(Long teacherId) {
        Teacher teacherById = em.find(Teacher.class, teacherId);
        em.remove(teacherById);
    }

    @Override
    @Transactional
    public void updateTeacher(Long teacherId, Teacher teacher) {
        Teacher teacherById = em.find(Teacher.class, teacherId);
        teacherById.setFirstname(teacher.getFirstname());
        teacherById.setLastname(teacher.getLastname());
        teacherById.setEmail(teacher.getEmail());
        teacherById.setPassword(teacher.getPassword());
        teacherById.setBio(teacher.getBio());
        teacherById.setBirthday(teacher.getBirthday());
        em.merge(teacherById);
    }
    
    
    

}
