package com.school.SpringSecuritywithDatabase.model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ApplicationController {
    @GetMapping
    public String testing(){
        return "passed the spring security through DB";
    }
}
