package com.school.SpringSecuritywithDatabase.service;
import com.school.SpringSecuritywithDatabase.model.Courses;
import com.school.SpringSecuritywithDatabase.model.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);

    List<Student> findAll();
    String deleteById(int id);

    Student findById(int id);

    List<Courses> findAllCoursesByStudentId(int id);


}
