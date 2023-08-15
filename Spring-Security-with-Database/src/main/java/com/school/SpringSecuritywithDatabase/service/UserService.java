package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.dao.UserDao;
import com.school.SpringSecuritywithDatabase.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@ConditionalOnMissingBean(UserServiceAdapter.class)
public class UserService {
    @Autowired
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserService() {
    }

    public List<User> numberOfUsers(int limit){
        List<User> allUsers = userDao.findAll();
        if(limit==0){
            return Collections.emptyList();
        }
        int lowerLimit = Math.min(limit, allUsers.size());
        return allUsers.subList(0,lowerLimit);
    }
}
