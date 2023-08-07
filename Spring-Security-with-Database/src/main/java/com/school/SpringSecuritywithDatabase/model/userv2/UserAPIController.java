package com.school.SpringSecuritywithDatabase.model.userv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/userApi")
public class UserAPIController {
    @Autowired
    private UserAPIService userAPIService;

    public UserAPIController(UserAPIService userAPIService) {
        this.userAPIService = userAPIService;
    }
    @GetMapping
    public List<UserAPI> getRandomUsers(@RequestParam int x){
        return userAPIService.getRandomUsers(x);
    }

    @PostMapping
    public UserAPI addUser(UserAPI userAPI){
        return userAPIService.addUser(userAPI);
    }
}
