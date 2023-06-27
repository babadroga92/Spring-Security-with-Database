package com.school.SpringSecuritywithDatabase.controller;

import com.school.SpringSecuritywithDatabase.model.CoursesTaken;
import com.school.SpringSecuritywithDatabase.model.Student;
import com.school.SpringSecuritywithDatabase.service.CoursesTakenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<CoursesTaken> findAll(){
        return coursesTakenServiceImpl.findAll();
    }

}
