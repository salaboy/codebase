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
    
    

}
