package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.dao.CoursesTakenDao;
import com.school.SpringSecuritywithDatabase.dto.CourseDTO;
import com.school.SpringSecuritywithDatabase.dto.StudentDTO;
import com.school.SpringSecuritywithDatabase.enums.Grade;
import com.school.SpringSecuritywithDatabase.model.Course;
import com.school.SpringSecuritywithDatabase.model.CoursesTaken;
import com.school.SpringSecuritywithDatabase.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class CoursesTakenServiceImplTest extends AbstractServiceImplTest{

    private CoursesTakenDao coursesTakenDao;

    private CoursesTakenServiceImpl coursesTakenServiceImpl;
    private CoursesTaken coursesTaken;

    private Student student;
    private Course course;
    private Grade grade;

    final static String GORDANA = "Gordana";

    @BeforeEach
    void setUp(){
        coursesTakenDao = Mockito.mock(CoursesTakenDao.class);
        coursesTakenServiceImpl = new CoursesTakenServiceImpl(coursesTakenDao);
        this.course = new Course("Java", null, null);
        List<Course> courseList = new ArrayList<>();
        courseList.add(this.course);
        this.student = new Student("Nina Bozic", courseList, null);
        this.grade = Grade.A;
        this.coursesTaken = new CoursesTaken(this.student, this.course, this.grade);
    }

    @Test
    void addCoursesTaken() {
        when(coursesTakenDao.save(any())).thenReturn(this.coursesTaken);
        CoursesTaken serviceCourseTaken = coursesTakenServiceImpl.addCoursesTaken(this.coursesTaken);
        assertNotNull(serviceCourseTaken);
        assertEquals(this.coursesTaken.getCourse(), serviceCourseTaken.getCourse());
    }

    @Test
    void findAll() {
        List<CoursesTaken> coursesTakenList = new ArrayList<>();
        coursesTakenList.add(this.coursesTaken);
        when(coursesTakenDao.findAll()).thenReturn(coursesTakenList);
        List<CoursesTaken> serviceList = coursesTakenServiceImpl.findAll();
        assertEquals(coursesTakenList.size(), serviceList.size());
    }

    @Test
    void findById() {
        when(coursesTakenDao.findById(anyInt())).thenReturn(Optional.of(this.coursesTaken));
        CoursesTaken serviceCoursesTaken = coursesTakenServiceImpl.findById(this.coursesTaken.getId());
        assertEquals(this.coursesTaken.getId(), serviceCoursesTaken.getCourse().getId());
        assertEquals(this.coursesTaken.getGrade(), serviceCoursesTaken.getGrade());
        assertEquals(this.coursesTaken.getCourse().getName(), serviceCoursesTaken.getCourse().getName());
    }

    @Test
    void deleteById() {
        when(coursesTakenDao.findById(anyInt())).thenReturn(Optional.of(this.coursesTaken));
        String serviceResult = coursesTakenServiceImpl.deleteById(this.coursesTaken.getId());
        assertEquals("Deletion successful", serviceResult);
    }

    @Test
    void findAllCoursesByStudentName() {
        List<String> coursesList = new ArrayList<>();
        coursesList.add(this.course.getName());
        when(coursesTakenDao.findAllCoursesByStudentName(GORDANA)).thenReturn(coursesList);
        List<String> serviceList = coursesTakenServiceImpl.findAllCoursesByStudentName(GORDANA);
        assertNotNull(serviceList);
        assertEquals(coursesList.size(), serviceList.size());
    }

    @Test
    void findAllCoursesByStudentNameDto() {
        CourseDTO java = new CourseDTO("java", null, null);
        CourseDTO python = new CourseDTO("python", null, null);
        List<CourseDTO> courseDTOList = new ArrayList<>();
        courseDTOList.add(java);
        courseDTOList.add(python);
        when(coursesTakenDao.findAllCoursesByStudentNameDto(GORDANA)).thenReturn(courseDTOList);
        List<CourseDTO> serviceList = coursesTakenServiceImpl.findAllCoursesByStudentNameDto(GORDANA);
        assertNotNull(serviceList);
        assertEquals(courseDTOList.size(), serviceList.size());
    }

    @Test
    void findNumberOfCourseTakenByStudents() {

        int numberOfClasses = 5;
        when(coursesTakenDao.findNumberOfCourseTakenByStudents(GORDANA)).thenReturn(numberOfClasses);
        int serviceNumber = coursesTakenServiceImpl.findNumberOfCourseTakenByStudents(GORDANA);
        assertEquals(numberOfClasses, serviceNumber);
    }

    @Test
    void findAllStudents() {
        StudentDTO nemanja = new StudentDTO("Nemanja");
        StudentDTO dragana = new StudentDTO("Dragana");
        List<StudentDTO> studentDTOList = new ArrayList<>();
        studentDTOList.add(nemanja);
        studentDTOList.add(dragana);
        when(coursesTakenDao.findAllStudents(any(),any())).thenReturn(studentDTOList);
        List<StudentDTO> serviceList = coursesTakenServiceImpl.findAllStudents(any(), any());
        assertNotNull(serviceList);
        assertEquals(studentDTOList.size(), serviceList.size());
        assertEquals(studentDTOList.get(0).getName(), serviceList.get(0).getName());
    }
}