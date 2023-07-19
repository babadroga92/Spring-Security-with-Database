package com.school.SpringSecuritywithDatabase.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.school.SpringSecuritywithDatabase.dto.CourseDTO;
import com.school.SpringSecuritywithDatabase.dto.StudentDTO;
import com.school.SpringSecuritywithDatabase.enums.Grade;
import com.school.SpringSecuritywithDatabase.exc.WrongIdException;
import com.school.SpringSecuritywithDatabase.model.CoursesTaken;
import com.school.SpringSecuritywithDatabase.service.CoursesTakenServiceImpl;
import com.school.SpringSecuritywithDatabase.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/coursesTaken")
public class CoursesTakenController {
    @Autowired
    private CoursesTakenServiceImpl coursesTakenServiceImpl;


    @PostMapping("/add")
    public CoursesTaken coursesTaken(@RequestBody CoursesTaken coursesTaken){
        return this.coursesTakenServiceImpl.addCoursesTaken(coursesTaken);
    }

    @GetMapping("/list")
    @JsonView(View.ShowMinimal.class)
    public List<CoursesTaken> findAll(){
        return coursesTakenServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoursesTaken> findById(@PathVariable int id) throws WrongIdException {
        return new ResponseEntity<>(coursesTakenServiceImpl.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public String deleteCoursesTaken(@PathVariable int id){
        coursesTakenServiceImpl.deleteById(id);
        return "user with id: " + id + " deleted";
    }
    @GetMapping("/studentCourses")
    public List<String> findAllCoursesByStudentName(@RequestParam(value = "name") String name) {
        return coursesTakenServiceImpl.findAllCoursesByStudentName(name);
}
    @GetMapping("/dto")
    public List<CourseDTO> findAllCoursesByStudentNameDto(@RequestParam(value = "name") String name){
        return coursesTakenServiceImpl.findAllCoursesByStudentNameDto(name);
    }
    @GetMapping("/countOfStudents")
    public Integer findNumberOfCourseTakenByStudents(@RequestParam(value = "name")String name){
        return coursesTakenServiceImpl.findNumberOfCourseTakenByStudents(name);
    }
    @GetMapping("/findStudents")
    public List<StudentDTO> findAllStudents(@RequestParam(value = "grade")Grade grade,
                                            @RequestParam(value = "course") String course){
        return coursesTakenServiceImpl.findAllStudents(grade, course);
    }
}
