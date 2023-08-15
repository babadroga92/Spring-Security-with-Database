package com.school.SpringSecuritywithDatabase.controller;

import com.school.SpringSecuritywithDatabase.model.User;
import com.school.SpringSecuritywithDatabase.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/registration") // ova metoda ustvari otvara stranicu koja prima aplikaciju za novog usera
    public String register(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") User user) {
        customUserDetailsService.addUser(user);
        return "redirect:/success";
    }
    @GetMapping("/success")
    public String success(){
        return "success";
    }
}
