package com.school.SpringSecuritywithDatabase.controller;

import com.school.SpringSecuritywithDatabase.dto.UserDTO;
import com.school.SpringSecuritywithDatabase.model.User;
import com.school.SpringSecuritywithDatabase.dao.UserDao;

import com.school.SpringSecuritywithDatabase.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserDao userDao;
    @PostMapping("/register")
    public String addUser(@RequestBody User user){
        customUserDetailsService.addUser(user);
        return "User added";
    }

//    @DeleteMapping("/{id}")
//    public String deleteUser(@PathVariable int id){
//        userDao.deleteById(id);
//        return "user with id: " + id + " deleted";
//    }
    @PutMapping("/updatePsswd")
    public String updatePassword(@RequestBody  UserDTO userDTO){
        return this.customUserDetailsService.updatePassword(userDTO);
    }

}
