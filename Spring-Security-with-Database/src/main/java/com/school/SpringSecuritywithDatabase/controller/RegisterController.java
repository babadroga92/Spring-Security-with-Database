package com.school.SpringSecuritywithDatabase.controller;
import com.school.SpringSecuritywithDatabase.model.registration.RegistrationRequest;
import com.school.SpringSecuritywithDatabase.model.registration.RegistrationService;
import com.school.SpringSecuritywithDatabase.model.registration.StudentRegistrationRequest;
import com.school.SpringSecuritywithDatabase.model.registration.StudentRegistrationService;
import com.school.SpringSecuritywithDatabase.service.CustomUserDetailsService;
import com.school.SpringSecuritywithDatabase.service.StudentServiceImpl;
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
    @Autowired
    private StudentRegistrationService studentRegistrationService;
    @Autowired
    private StudentServiceImpl studentServiceImpl;

    public RegisterController(RegistrationService registrationService,
                              CustomUserDetailsService customUserDetailsService,
                              StudentRegistrationService studentRegistrationService,
                              StudentServiceImpl studentServiceImpl) {
        this.registrationService = registrationService;
        this.customUserDetailsService = customUserDetailsService;
        this.studentRegistrationService = studentRegistrationService;
        this.studentServiceImpl = studentServiceImpl;
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
    @GetMapping("/register")
    public String registering(Model model){
        model.addAttribute("student", new StudentRegistrationRequest());
        return "register";
    }
    @PostMapping("/register")
    public String addStudent(@ModelAttribute("student") StudentRegistrationRequest request){
        studentRegistrationService.register(request);
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
