package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.model.User;
import com.school.SpringSecuritywithDatabase.model.userApiGet.UserAPI;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserConverter {

    public List<User> convert(List<UserAPI> userAPIList){
        List<User> userList = new ArrayList<>(); //napravimo praznu listu
        if(userAPIList.size() >0){
        for(UserAPI userAPI : userAPIList) {
            User user = new User(); //novi user objekat
            user.setUsername(userAPI.getName().getFirst()); //za svakog novog usera koristi se for loop od userApiListe i setuje ime
            userList.add(user); // doda se u listu
        }
        }
        return userList;
//        return userAPIList.stream()
//                .map(userAPI -> {
//                    User user = new User();
//                    user.setUsername(userAPI.getName().getFirst());
//                    return user;
//                }).collect(Collectors.toList());

    }
}
