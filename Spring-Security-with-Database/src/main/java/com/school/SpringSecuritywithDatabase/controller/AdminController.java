package com.school.SpringSecuritywithDatabase.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.school.SpringSecuritywithDatabase.dao.UserDao;
import com.school.SpringSecuritywithDatabase.model.Course;
import com.school.SpringSecuritywithDatabase.model.Student;
import com.school.SpringSecuritywithDatabase.service.CustomUserDetailsService;
import com.school.SpringSecuritywithDatabase.service.StudentServiceImpl;
import com.school.SpringSecuritywithDatabase.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserDao userDao;

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        return customUserDetailsService.deleteById(id);
    }
    @GetMapping("/list")
    @JsonView(View.ShowMinimal.class)
    public List<Student> findAll(){
        return this.studentServiceImpl.findAll();
    }

}
