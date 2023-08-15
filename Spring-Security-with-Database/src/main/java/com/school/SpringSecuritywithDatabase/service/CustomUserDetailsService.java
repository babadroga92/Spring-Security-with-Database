package com.school.SpringSecuritywithDatabase.service;
import com.school.SpringSecuritywithDatabase.dao.UserDao;
import com.school.SpringSecuritywithDatabase.dto.UserDTO;
import com.school.SpringSecuritywithDatabase.email.EmailService;
import com.school.SpringSecuritywithDatabase.enums.Roles;
import com.school.SpringSecuritywithDatabase.exc.*;
import com.school.SpringSecuritywithDatabase.model.CustomUserDetails;
import com.school.SpringSecuritywithDatabase.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;

    public CustomUserDetailsService(UserDao userDao, BCryptPasswordEncoder passwordEncoder, EmailService emailService) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public CustomUserDetailsService() {
    }

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
        assert psswd != null;
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

    public void addUser(User user) throws ExceededNumberOfAdmins, UserWithUsernameAlreadyExists{
        User userdb = userDao.findByUsername(user.getUsername());
        if(userdb!=null){
            throw new UserWithUsernameAlreadyExists("This username already exists");
        }
        Integer numberOfAdmins = userDao.numberOfAdmins();
        if(user.getRoles()== Roles.ADMIN && numberOfAdmins>=2){
            throw new ExceededNumberOfAdmins("There is already 2 admin users in the system");
        }
        String pwd = user.getPassword();
        String encryptedPwd = passwordEncoder.encode(pwd);
        user.setPassword(encryptedPwd);
        userDao.save(user);
        emailService.sendAccountCreationNotification(user.getEmail());
    }

    public User findByEmail(String email) {
        Optional<User> optional = userDao.findByEmail(email);
        if(optional.isPresent()){
            return optional.get();
        }else {
          throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email));
        }
    }
    public String deleteById(int id) throws WrongIdException{
        Optional<User> optional = userDao.findById(id);
        if(optional.isPresent()){
            emailService.sendAccountDeletionNotification(optional.get().getEmail());
            userDao.deleteById(id);
            return "user deleted";
        }else {
            throw new WrongIdException("User with " + id + " doesn't exist.");
        }
    }

}

