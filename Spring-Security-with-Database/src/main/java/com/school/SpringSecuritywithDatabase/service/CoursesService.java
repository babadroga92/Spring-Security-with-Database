package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.model.Course;

import java.util.List;

public interface CoursesService {
    Course addCourse(Course course);
    List<Course> findAll();
    Course findById(int id);


}
