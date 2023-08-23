package com.school.SpringSecuritywithDatabase.controller;

import com.school.SpringSecuritywithDatabase.model.User;
import com.school.SpringSecuritywithDatabase.model.registration.RegistrationRequest;
import com.school.SpringSecuritywithDatabase.model.registration.RegistrationService;
import com.school.SpringSecuritywithDatabase.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public RegisterController(CustomUserDetailsService customUserDetailsService, RegistrationService registrationService) {
        this.customUserDetailsService = customUserDetailsService;
        this.registrationService = registrationService;
    }

    @GetMapping("/registration")
    public String register(Model model){
        model.addAttribute("user", new RegistrationRequest());
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") RegistrationRequest request) {
        registrationService.register(request);
        return "redirect:/success";
    }
    @GetMapping("/success")
    public String success(){
        return "success";
    }


    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
