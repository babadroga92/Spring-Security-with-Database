package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.dao.UserDao;
import com.school.SpringSecuritywithDatabase.model.CustomUserDetails;
import com.school.SpringSecuritywithDatabase.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user =  userDao.findByUsername(username);
        CustomUserDetails userDetails = null;
        if(user !=null){
          userDetails = new CustomUserDetails();
          userDetails.setUser(user);
       }else{
           throw new UsernameNotFoundException("User doesn't exist");
       }
       return userDetails;
    }
}
