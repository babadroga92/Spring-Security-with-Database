package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.dto.CourseDTO;
import com.school.SpringSecuritywithDatabase.enums.Grade;
import com.school.SpringSecuritywithDatabase.model.CoursesTaken;
import com.school.SpringSecuritywithDatabase.model.Student;


import java.util.List;


public interface CoursesTakenService {
    CoursesTaken addCoursesTaken(CoursesTaken coursesTaken);

    List<CoursesTaken> findAll();

    CoursesTaken findById(int id);
    String deleteById(int id);

    List<String> findAllCoursesByStudentName(String name);

    List<CourseDTO> findAllCoursesByStudentNameDto(String name);

    Integer findNumberOfCourseTakenByStudents(String name);

    List<Student> findAllStudents(Grade grade, String course);





}
