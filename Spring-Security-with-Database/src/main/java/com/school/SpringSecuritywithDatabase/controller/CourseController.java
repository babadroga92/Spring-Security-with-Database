package com.school.SpringSecuritywithDatabase.controller;

import com.school.SpringSecuritywithDatabase.exc.WrongIdException;
import com.school.SpringSecuritywithDatabase.model.Course;
import com.school.SpringSecuritywithDatabase.service.CoursesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CoursesServiceImpl coursesServiceImpl;

    @PostMapping("/add")
    public Course addCourse(@RequestBody Course course){
        return this.coursesServiceImpl.addCourse(course);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable int id) throws WrongIdException{
        return new ResponseEntity<>(coursesServiceImpl.findById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Course> findAll(){
        return this.coursesServiceImpl.findAll();
    }


}
