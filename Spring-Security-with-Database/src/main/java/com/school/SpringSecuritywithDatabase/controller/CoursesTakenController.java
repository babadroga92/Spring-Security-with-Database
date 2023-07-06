package com.school.SpringSecuritywithDatabase.controller;

import com.fasterxml.jackson.annotation.JsonView;
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

}
