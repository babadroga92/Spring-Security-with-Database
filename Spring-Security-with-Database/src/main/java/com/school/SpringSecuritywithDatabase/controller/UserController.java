package com.school.SpringSecuritywithDatabase.controller;
import com.school.SpringSecuritywithDatabase.dto.UserDTO;
import com.school.SpringSecuritywithDatabase.model.User;
import com.school.SpringSecuritywithDatabase.dao.UserDao;
import com.school.SpringSecuritywithDatabase.service.CustomUserDetailsService;
import com.school.SpringSecuritywithDatabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @PutMapping("/updatePsswd")
    public String updatePassword(@RequestBody UserDTO userDTO) {
        return this.customUserDetailsService.updatePassword(userDTO);
    }

    @GetMapping("/{email}")
    public User findByEmail(@PathVariable("email") String email){
        return this.customUserDetailsService.findByEmail(email);
    }

    @GetMapping("/listOfUsersByNumber")
    public List<User> numberOfUsers(@RequestParam int limit){
        return this.userService.numberOfUsers(limit);
    }
}
