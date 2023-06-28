package com.school.SpringSecuritywithDatabase.service;


import com.school.SpringSecuritywithDatabase.model.CoursesTaught;
import com.school.SpringSecuritywithDatabase.model.CoursesTaughtRequest;

public interface CoursesTaughtService {
    CoursesTaught addCoursesTaught(CoursesTaught coursesTaught);

    CoursesTaught findById(int id);

    CoursesTaught updateCourseId(CoursesTaughtRequest request, int id);

}
