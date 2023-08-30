package com.school.SpringSecuritywithDatabase.service;
import com.school.SpringSecuritywithDatabase.dao.UserDao;
import com.school.SpringSecuritywithDatabase.model.User;
import com.school.SpringSecuritywithDatabase.model.userApiGet.UserAPI;
import com.school.SpringSecuritywithDatabase.model.userApiGet.UserAPIService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnExpression("${external-api-active:true}")
public class UserServiceAdapter extends UserService {
    private UserAPIService userAPIService;
    private UserConverter userConverter;
    public UserServiceAdapter(UserDao userDao, UserAPIService userAPIService, UserConverter userConverter) {
        super(userDao);
        this.userAPIService = userAPIService;
        this.userConverter = userConverter;
    }
    @Override
    public List<User> numberOfUsers(int limit){
        List<UserAPI> userList = userAPIService.getRandomUsers(limit);
        return userConverter.convert(userList);
    }
}
