package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.model.User;
import com.school.SpringSecuritywithDatabase.model.userApiGet.Name;
import com.school.SpringSecuritywithDatabase.model.userApiGet.UserAPI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserConverterTest extends AbstractServiceImplTest{
    @Autowired
    private UserConverter userConverter;
    @Test
    void convert() {
        List<UserAPI> list = userAPIList();
        List<User> userList = userConverter.convert(list);
        assertEquals(userList.size(), list.size());
        assertEquals(userList.get(0).getUsername(), list.get(0).getName().getFirst());
        assertEquals(userList.get(1).getUsername(), list.get(1).getName().getFirst());
    }
    @Test
    void convertIsNameNull() {
        List<UserAPI> list = userAPIList();
        list.get(0).setName(null);
        list.get(1).setName(null);
        List<User> userList = userConverter.convert(list);
        assertEquals(userList.size(), list.size());
        assertNull(userList.get(0).getUsername());
        assertNull(userList.get(1).getUsername());
    }
    private List<UserAPI> userAPIList(){
        UserAPI userAPI1 = new UserAPI("male", new Name("Nemanja", "Milanovic"));
        UserAPI userAPI2 = new UserAPI("female", new Name("Dragana", "Pavlovic"));
        List<UserAPI> userAPIList = new ArrayList<>();
        userAPIList.add(userAPI1);
        userAPIList.add(userAPI2);
        return userAPIList;
    }
}