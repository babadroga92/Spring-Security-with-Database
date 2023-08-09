package com.school.SpringSecuritywithDatabase.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private  JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendAccountCreationNotification(String toEmail){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject("Account Created");
        mailMessage.setText("Congratulations! Your account has been successfully created.");
        javaMailSender.send(mailMessage);
    }

    public void sendAccountDeletionNotification(String toEmail){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject("Account Deleted");
        mailMessage.setText("This is a confirmation that your account has been deleted.");
        javaMailSender.send(mailMessage);
    }
}
