package com.school.SpringSecuritywithDatabase.service;
import com.school.SpringSecuritywithDatabase.model.Course;
import com.school.SpringSecuritywithDatabase.model.CoursesTaken;
import com.school.SpringSecuritywithDatabase.model.Student;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);

    List<Student> findAll();
    String deleteById(int id);

    Student findById(int id);

    List<Course> findAllCoursesByStudentId(int id);
    List<String> findByStudentAndProfessor(int studentId, int professorId);
    Student  updateStudentsName(int id, Student student);

    void findAllStudentsTWithoutUserId();

    void findAllStudentsTWithoutUserIdCustomScheduling();


}
