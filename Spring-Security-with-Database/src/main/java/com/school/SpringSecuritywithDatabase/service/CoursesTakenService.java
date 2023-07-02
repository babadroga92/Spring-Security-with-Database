package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.model.CoursesTaken;


import java.util.List;


public interface CoursesTakenService {
    CoursesTaken addCoursesTaken(CoursesTaken coursesTaken);

    List<CoursesTaken> findAll();

    CoursesTaken findById(int id);
    String deleteById(int id);





}
