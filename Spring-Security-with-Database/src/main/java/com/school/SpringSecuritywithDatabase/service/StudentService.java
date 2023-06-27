package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.model.CoursesTaken;
import com.school.SpringSecuritywithDatabase.model.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);

    List<Student> findAll();
    String deleteById(int id);

//    List<CoursesTaken> findByStudent(int studentId);


}