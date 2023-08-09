package com.school.SpringSecuritywithDatabase.controller;

import com.school.SpringSecuritywithDatabase.dao.UserDao;
import com.school.SpringSecuritywithDatabase.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserDao userDao;

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        return customUserDetailsService.deleteById(id);

    }
}
