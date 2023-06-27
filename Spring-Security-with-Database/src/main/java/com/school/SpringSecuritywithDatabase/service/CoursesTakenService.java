package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.model.CoursesTaken;
import com.school.SpringSecuritywithDatabase.model.Student;

import java.util.List;


public interface CoursesTakenService {
    CoursesTaken addCoursesTaken(CoursesTaken coursesTaken);

    List<CoursesTaken> findAll();





}
