package com.school.SpringSecuritywithDatabase.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.school.SpringSecuritywithDatabase.exc.WrongIdException;
import com.school.SpringSecuritywithDatabase.model.Course;
import com.school.SpringSecuritywithDatabase.model.Student;
import com.school.SpringSecuritywithDatabase.service.StudentServiceImpl;
import com.school.SpringSecuritywithDatabase.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Student> addStudent(@RequestBody @Valid Student student){
       return new ResponseEntity<>(studentService.addStudent(student), HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<Student> findAll(){
        return this.studentService.findAll();
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id){
        return studentService.deleteById(id);
    }

    @GetMapping("/{id}")
    @JsonView(View.ShowMinimal.class)
    public ResponseEntity<Student> findById(@PathVariable int id) throws WrongIdException {
    return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/listOfCourses")
    public List<Course> findAllCoursesByStudentId(@PathVariable int id){
        return studentService.findAllCoursesByStudentId(id);
    }

    @PutMapping("/{id}/update")
    public Student updateStudentsName(@PathVariable int id, @RequestBody Student student){
        return  studentService.updateStudentsName(id,student);
    }
}
