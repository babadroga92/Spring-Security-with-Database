package com.school.SpringSecuritywithDatabase.controller;

import com.school.SpringSecuritywithDatabase.exc.WrongIdException;
import com.school.SpringSecuritywithDatabase.model.Courses;
import com.school.SpringSecuritywithDatabase.model.CoursesTaken;
import com.school.SpringSecuritywithDatabase.model.Student;
import com.school.SpringSecuritywithDatabase.service.StudentService;
import com.school.SpringSecuritywithDatabase.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentServiceImpl studentService;
    @Autowired
    public StudentController(StudentServiceImpl studentService) {
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

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable int id) throws WrongIdException {
    return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }
    @GetMapping("/{id}/listOfCourses")
    public List<Courses> findAllCoursesByStudentId(@PathVariable int id){
        return studentService.findAllCoursesByStudentId(id);
    }
}
