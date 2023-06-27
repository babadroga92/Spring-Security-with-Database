package com.school.SpringSecuritywithDatabase.controller;

import com.school.SpringSecuritywithDatabase.exc.WrongIdException;
import com.school.SpringSecuritywithDatabase.model.Courses;
import com.school.SpringSecuritywithDatabase.service.CoursesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CoursesController {
    @Autowired
    private CoursesServiceImpl coursesServiceImpl;

    @PostMapping("/add")
    public Courses addCourse(@RequestBody Courses courses){
        return this.coursesServiceImpl.addCourse(courses);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Courses> findById(@PathVariable int id) throws WrongIdException{
        return new ResponseEntity<>(coursesServiceImpl.findById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Courses> findAll(){
        return this.coursesServiceImpl.findAll();
    }


}
