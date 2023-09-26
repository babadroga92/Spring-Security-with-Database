package com.school.SpringSecuritywithDatabase.service;
import com.school.SpringSecuritywithDatabase.dao.UserDao;
import com.school.SpringSecuritywithDatabase.dto.UserDTO;
import com.school.SpringSecuritywithDatabase.email.EmailService;
import com.school.SpringSecuritywithDatabase.enums.Roles;
import com.school.SpringSecuritywithDatabase.exc.*;
import com.school.SpringSecuritywithDatabase.model.CustomUserDetails;
import com.school.SpringSecuritywithDatabase.model.Student;
import com.school.SpringSecuritywithDatabase.model.User;
import com.school.SpringSecuritywithDatabase.model.registration.token.ConfirmationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ConfirmationTokenServiceImpl confirmationTokenServiceImpl;

    public CustomUserDetailsService(UserDao userDao,
                                    BCryptPasswordEncoder passwordEncoder,
                                    EmailService emailService,
                                    ConfirmationTokenServiceImpl confirmationTokenServiceImpl) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.confirmationTokenServiceImpl = confirmationTokenServiceImpl;
    }

    public CustomUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user =  userDao.findByUsername(username);
        CustomUserDetails userDetails;
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

    public String addUser(User user) throws ExceededNumberOfAdmins, UserWithUsernameAlreadyExists{
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

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(24),
                user
        );
        confirmationTokenServiceImpl.saveConfirmationToken(
                confirmationToken
        );
        return token;
    }
    public int enableUser(String email){
        return userDao.enableUser(email);
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
            User user = optional.get();
            user.setCanBeDeleted(true);
            userDao.save(user);
            return "User is scheduled to be deleted in next 24 hours";
        }else {
            throw new WrongIdException("User with " + id + " doesn't exist.");
        }
    }
    @Scheduled(cron = "${user-deletion-cron}")
    public void deleteAll() {
        List<User> userList = userDao.findByCanBeDeleted(true);
        logger.info("Start of the process of eliminating all the students without associated user.");
        userDao.deleteAll(userList);
    }
}

