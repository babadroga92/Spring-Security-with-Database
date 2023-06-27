package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.model.Courses;

import java.util.List;

public interface CoursesService {
    Courses addCourse(Courses courses);
    List<Courses> findAll();
    Courses findById(int id);
}
