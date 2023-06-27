package com.school.SpringSecuritywithDatabase.controller;

import com.school.SpringSecuritywithDatabase.model.CoursesTaught;
import com.school.SpringSecuritywithDatabase.service.CoursesTaughtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coursesTaught")
public class CoursesTaughtController {

    @Autowired
    private CoursesTaughtServiceImpl coursesTaughtServiceImpl;

    @PostMapping("/add")
    public CoursesTaught coursesTaught(@RequestBody CoursesTaught coursesTaught){
        return this.coursesTaughtServiceImpl.addCoursesTaught(coursesTaught);
    }
}
