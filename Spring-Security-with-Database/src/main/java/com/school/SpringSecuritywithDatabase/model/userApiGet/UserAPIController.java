package com.school.SpringSecuritywithDatabase.model.userApiGet;

import com.school.SpringSecuritywithDatabase.model.userApiPost.UserApiPost;
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
    public UserApiPost addUser(UserApiPost userApiPost){
        return userAPIService.addUser(userApiPost);
    }
}
