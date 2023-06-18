package com.school.SpringSecuritywithDatabase.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    @GetMapping
    public String testing(){
        return "passed the spring security through DB";
    }

    @GetMapping("/read")
    public String read(){
        return "read";
    }

    @GetMapping("/write")
    public String write(){
        return "write";
    }
}
