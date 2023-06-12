package com.school.SpringSecuritywithDatabase.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure/auth")
public class AdminController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/admin/add")
    public String addUser(@RequestBody User user){
        String pwd = user.getPassword();
        String encryptedPwd = passwordEncoder.encode(pwd);
        user.setPassword(encryptedPwd);
        userRepository.save(user);
        return "User added";
    }
    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }
}
