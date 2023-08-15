package com.school.SpringSecuritywithDatabase.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.school.SpringSecuritywithDatabase.exc.WrongIdException;
import com.school.SpringSecuritywithDatabase.model.Course;
import com.school.SpringSecuritywithDatabase.model.CoursesTaken;
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
    private StudentServiceImpl studentServiceImpl;
    @Autowired
    public StudentController(StudentServiceImpl studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody @Valid Student student){
       return new ResponseEntity<>(studentServiceImpl.addStudent(student), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id){
        return studentServiceImpl.deleteById(id);
    }

    @GetMapping("/{id}")
    @JsonView(View.ShowMinimal.class)
    public ResponseEntity<Student> findById(@PathVariable int id) throws WrongIdException {
    return new ResponseEntity<>(studentServiceImpl.findById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/listOfCourses")
    public List<Course> findAllCoursesByStudentId(@PathVariable int id){
        return studentServiceImpl.findAllCoursesByStudentId(id);
    }

    @PutMapping("/{id}/update")
    public Student updateStudentsName(@PathVariable int id, @RequestBody Student student){
        return  studentServiceImpl.updateStudentsName(id,student);
    }

    @GetMapping("/{studentId}/{professorId}/courses")
    public List<String> findByStudentAndProfessor(@PathVariable int studentId, @PathVariable int professorId){
        return studentServiceImpl.findByStudentAndProfessor(studentId,professorId);
    }
}
