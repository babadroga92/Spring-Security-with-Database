package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.dao.CourseDao;
import com.school.SpringSecuritywithDatabase.model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class CoursesServiceImplTest {

    private CourseDao courseDao;

    private CoursesServiceImpl coursesServiceImpl;

    private Course course;

    @BeforeEach
    void setUp(){
        courseDao = Mockito.mock(CourseDao.class);
        coursesServiceImpl = new CoursesServiceImpl(courseDao);
        this.course = new Course("Programiranje", null, null);
    }

    @Test
    void addCourse() {
        when(courseDao.save(any())).thenReturn(this.course);
        Course serviceCourse = coursesServiceImpl.addCourse(course);
        assertNotNull(serviceCourse);
        assertEquals(this.course.getName(), serviceCourse.getName());
    }

    @Test
    void findAll() {
        List<Course> courseList = new ArrayList<>();
        courseList.add(course);
        when(courseDao.findAll()).thenReturn(courseList);
        List<Course> serviceList = coursesServiceImpl.findAll();
        assertEquals(courseList.size(), serviceList.size());
    }

    @Test
    void findById() {
        when(courseDao.findById(anyInt())).thenReturn(Optional.of(this.course));
        Course serviceCourse = coursesServiceImpl.findById(this.course.getId());
        assertEquals(this.course.getName(), serviceCourse.getName());
    }
}