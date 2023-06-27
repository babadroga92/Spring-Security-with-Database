package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.dao.UserDao;
import com.school.SpringSecuritywithDatabase.dto.UserDTO;
import com.school.SpringSecuritywithDatabase.exc.DidntAddException;
import com.school.SpringSecuritywithDatabase.exc.PasswordsDoNotMatch;
import com.school.SpringSecuritywithDatabase.model.CustomUserDetails;
import com.school.SpringSecuritywithDatabase.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
    public String updatePassword(UserDTO userDTO) {
        User user = userDao.findByUsername(userDTO.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("User 404");
        }
        String psswd = userDTO.getPassword();
        String secondPsswd = userDTO.getRepeatPassword();
        if (psswd == null && secondPsswd == null) {
            throw new DidntAddException("you need to input both passwords");
        }
        if (psswd.equals(secondPsswd)) {
            String encryptedPwd = passwordEncoder.encode(psswd);
            if (encryptedPwd.equals(user.getPassword())) {
                throw new RuntimeException("Passwords are the same");
            } else {
                user.setPassword(encryptedPwd);
                userDao.save(user);
            }

        }
        else{
            throw new PasswordsDoNotMatch("passwords do not match");
        }
      return "finally";
    }

    public String addUser(User user){
        String pwd = user.getPassword();
        String encryptedPwd = passwordEncoder.encode(pwd);
        user.setPassword(encryptedPwd);
        userDao.save(user);
        return "user saved";
    }
}

