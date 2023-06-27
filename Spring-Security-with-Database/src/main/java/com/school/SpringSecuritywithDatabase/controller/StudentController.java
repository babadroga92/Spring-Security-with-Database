package com.school.SpringSecuritywithDatabase.controller;

import com.school.SpringSecuritywithDatabase.model.CoursesTaken;
import com.school.SpringSecuritywithDatabase.model.Student;
import com.school.SpringSecuritywithDatabase.service.StudentService;
import com.school.SpringSecuritywithDatabase.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;
@Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public String addStudent(@RequestBody @Valid Student student){
        studentService.addStudent(student);
        return "Student added";
    }

    @GetMapping("/list")
    public List<Student> findAll(){
        return this.studentService.findAll();
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id){
        studentService.deleteById(id);
        return "student with id: " + id + " deleted";
    }
//    @GetMapping("/allCourses")
//    public List<CoursesTaken> findByStudent(@RequestParam(name = "studentId") int studentId){
//        return studentService.findByStudent(studentId);
//    }
}
