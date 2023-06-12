package com.security.Spring.Boot.Security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/student")
    @ResponseBody
    public String studentHome() {
        return "Welcome, Student!";
    }

    @GetMapping("/professor")
    @ResponseBody
    public String professorHome() {
        return "Welcome, Professor!";
    }

    @GetMapping("/assistant")
    @ResponseBody
    public String assistantHome() {
        return "Welcome, Professor's Assistant!";
    }

    @GetMapping("/user")
    @ResponseBody
    public String userHome() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return "Welcome, " + username + "!";
    }
}
