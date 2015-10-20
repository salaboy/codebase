/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.core.school.tests;

import java.util.List;
import javax.inject.Inject;

import org.codebase.core.exceptions.ServiceException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@RunWith(Arquillian.class)
public class SchoolsServiceSETest {

    @Inject
    private SchoolsService schoolsService;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Deployment
    public static JavaArchive createDeployment() {

        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "org.codebase")
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/users.xml", "users.xml")
                .addAsManifestResource("META-INF/school.xml", "school.xml")
                .addAsManifestResource("META-INF/teacher.xml", "teacher.xml")
                .addAsManifestResource("META-INF/year.xml", "year.xml")
                .addAsManifestResource("META-INF/schoolclass.xml", "schoolclass.xml")
                .addAsManifestResource("META-INF/student.xml", "student.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void newCourseInNewSchoolTest() throws ServiceException, Exception {

        School school = new School();
        school.setContactEmail("contact@school.net");
        school.setDescription("new school in town");
        school.setSchoolName("New School");
        school.setWebsite("www.website.com");
        Long newSchoolId = schoolsService.newSchool(school);
        Assert.assertNotNull(newSchoolId);
        

        Teacher teacher = new Teacher("firstTeacher@gmail.com", "asdasd");
        
        Long newTeacherId = schoolsService.newTeacher(teacher);
        Assert.assertNotNull(newTeacherId);
        Course course = new Course();
        course.setSubject("Maths");
        course.setSchool(school);
        course.setTeacher(teacher);
        
        Long newCourseId = schoolsService.newCourse(course);
        Assert.assertNotNull(newCourseId);
        Year year = new Year();
        year.setSchool(school);
        year.setYearName("2015");
        Long newYearId = schoolsService.newYear(year);
        Assert.assertNotNull(newYearId);
        Student student = new Student("student@email.com", "asdasd");
        Long newStudentId = schoolsService.newStudent(student);
        Assert.assertNotNull(newStudentId);
        
        
        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setCourse(course);
        schoolClass.setYear(year);
        schoolClass.setCode("2015Math");
        schoolClass.enrollStudent(student);
        Long newScoolClassId = schoolsService.newSchoolClass(schoolClass);
        
        Assert.assertNotNull(newScoolClassId);
        
        Student student2 = new Student("student2@email.com", "asdasd");
        Long newStudentId2 = schoolsService.newStudent(student2);
        Assert.assertNotNull(newStudentId2);
        
        schoolsService.enrollStudentToSchoolClass(newScoolClassId, student2);
        
        List<Year> yearsBySchool = schoolsService.getYearsBySchool(newSchoolId);
        Assert.assertEquals(1, yearsBySchool.size());
        
        Year year2016 = new Year();
        year2016.setSchool(school);
        year2016.setYearName("2016");
        Long newYearId2016 = schoolsService.newYear(year2016);
        Assert.assertNotNull(newYearId2016);
        
        yearsBySchool = schoolsService.getYearsBySchool(newSchoolId);
        Assert.assertEquals(2, yearsBySchool.size());

        
        List<Course> coursesBySchool = schoolsService.getCoursesBySchool(newSchoolId);
        Assert.assertEquals(1, coursesBySchool.size());
        
        Course courseEnglish = new Course();
        courseEnglish.setSubject("English");
        courseEnglish.setSchool(school);
        courseEnglish.setTeacher(teacher);
        
        Long newCourseIdEnglish = schoolsService.newCourse(courseEnglish);
        Assert.assertNotNull(newCourseIdEnglish);
        
        coursesBySchool = schoolsService.getCoursesBySchool(newSchoolId);
        Assert.assertEquals(2, coursesBySchool.size());

        List<SchoolClass> schoolClassesByYear = schoolsService.getSchoolClassByYear(newSchoolId, "2015");
        Assert.assertEquals(1, schoolClassesByYear.size());
        
        SchoolClass schoolClassEnglish = new SchoolClass();
        schoolClassEnglish.setCourse(courseEnglish);
        schoolClassEnglish.setYear(year2016);
        schoolClassEnglish.setCode("2016English");
        
        Long newScoolClassIdEnglish2016 = schoolsService.newSchoolClass(schoolClassEnglish);
        Assert.assertNotNull(newScoolClassIdEnglish2016);
        
        schoolClassesByYear = schoolsService.getSchoolClassByYear(newSchoolId, "2015");
        Assert.assertEquals(1, schoolClassesByYear.size());
        
        schoolClassesByYear = schoolsService.getSchoolClassByYear(newSchoolId, "2016");
        Assert.assertEquals(1, schoolClassesByYear.size());
        
        List<Student> students = schoolsService.getStudentsBySchoolClassCode(newSchoolId, "2015Math");
        Assert.assertEquals(2, students.size());
    }
    
    

    

    

}
