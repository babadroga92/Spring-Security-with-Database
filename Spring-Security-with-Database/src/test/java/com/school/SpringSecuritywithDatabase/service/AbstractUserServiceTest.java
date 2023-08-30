package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class  AbstractUserServiceTest extends AbstractServiceImplTest{
    @Autowired
    private UserService userService;
    @Test
    void numberOfUsers(){
        List<User> userList = userService.numberOfUsers(2);
        assertEquals(userList.size(), 2);
    }
}
