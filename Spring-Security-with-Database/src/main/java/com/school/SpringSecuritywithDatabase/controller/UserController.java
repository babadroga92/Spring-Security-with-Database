package com.school.SpringSecuritywithDatabase.controller;
import com.school.SpringSecuritywithDatabase.dto.UserDTO;
import com.school.SpringSecuritywithDatabase.model.User;
import com.school.SpringSecuritywithDatabase.dao.UserDao;
import com.school.SpringSecuritywithDatabase.model.registration.RegistrationRequest;
import com.school.SpringSecuritywithDatabase.model.registration.RegistrationService;
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
    private RegistrationService registrationService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    public UserController(CustomUserDetailsService customUserDetailsService,
                          UserService userService,
                          UserDao userDao,
                          RegistrationService registrationService) {
        this.customUserDetailsService = customUserDetailsService;
        this.userService = userService;
        this.userDao = userDao;
        this.registrationService = registrationService;
    }

    @PutMapping("/updatePsswd")
    public String updatePassword(@RequestBody UserDTO userDTO) {
        return this.customUserDetailsService.updatePassword(userDTO);
    }
    @PostMapping("/registration")
    public void addUser(@RequestBody RegistrationRequest request) {
        registrationService.register(request);
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
