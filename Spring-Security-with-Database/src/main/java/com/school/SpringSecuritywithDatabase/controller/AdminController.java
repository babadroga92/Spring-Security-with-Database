package com.school.SpringSecuritywithDatabase.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.school.SpringSecuritywithDatabase.dao.UserDao;

import com.school.SpringSecuritywithDatabase.model.Student;
import com.school.SpringSecuritywithDatabase.service.ConfirmationTokenServiceImpl;
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
    @Autowired
    private ConfirmationTokenServiceImpl confirmationTokenServiceImpl;

    public AdminController(CustomUserDetailsService customUserDetailsService, UserDao userDao, StudentServiceImpl studentServiceImpl, ConfirmationTokenServiceImpl confirmationTokenServiceImpl) {
        this.customUserDetailsService = customUserDetailsService;
        this.userDao = userDao;
        this.studentServiceImpl = studentServiceImpl;
        this.confirmationTokenServiceImpl = confirmationTokenServiceImpl;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        return customUserDetailsService.deleteById(id);
    }
    @GetMapping("/list")
    @JsonView(View.ShowMinimal.class)
    public List<Student> findAll(){
        return this.studentServiceImpl.findAll();
    }

    @DeleteMapping("/tokenDeletion/{id}")
    public String deleteToken(@PathVariable int id){
        confirmationTokenServiceImpl.deleteById(id);
        return "Token deleted";
    }

}
