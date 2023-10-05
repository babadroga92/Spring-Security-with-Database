package com.school.SpringSecuritywithDatabase.model.registration;

import com.school.SpringSecuritywithDatabase.dao.UserDao;
import com.school.SpringSecuritywithDatabase.email.EmailService;
import com.school.SpringSecuritywithDatabase.enums.Roles;
import com.school.SpringSecuritywithDatabase.exc.EmailAlreadyInUse;
import com.school.SpringSecuritywithDatabase.model.User;
import com.school.SpringSecuritywithDatabase.model.registration.token.ConfirmationToken;
import org.springframework.stereotype.Service;
import com.school.SpringSecuritywithDatabase.service.ConfirmationTokenServiceImpl;
import com.school.SpringSecuritywithDatabase.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RegistrationService {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private ConfirmationTokenServiceImpl confirmationTokenService;
    @Autowired
    private EmailService emailService;
    private final UserDao userDao;

    public RegistrationService(CustomUserDetailsService customUserDetailsService, ConfirmationTokenServiceImpl confirmationTokenService, EmailService emailService,
                               UserDao userDao) {
        this.customUserDetailsService = customUserDetailsService;
        this.confirmationTokenService = confirmationTokenService;
        this.emailService = emailService;
        this.userDao = userDao;
    }
    public String register(RegistrationRequest request){
        Roles role = request.getRole();
        Optional<User> findByEmail = userDao.findByEmail(request.getEmail());
        if(findByEmail.isPresent()){
            throw new EmailAlreadyInUse("This email is already in use");
        }
        String token = customUserDetailsService.addUser(
                new User(
                        request.getUsername(),
                        request.getPassword(),
                        request.getEmail(),
                        role
                )
        );
        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
        emailService.send(
                request.getEmail(),
                buildEmail(request.getEmail(), link));
        return token;
    }
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .findByToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        customUserDetailsService.enableUser(
                confirmationToken.getUser().getEmail());
        return "confirmed";
    }


    private String buildEmail(String name, String link) {
            return "<div style=\"font-family: Helvetica, Arial, sans-serif; font-size: 16px; margin: 0; color: #0b0c0c\">\n" +
                    "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse: collapse; width: 100% !important;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                    "    <tr>\n" +
                    "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                    "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse: collapse; max-width: 580px;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                    "          <tr>\n" +
                    "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\"></td>\n" +
                    "            <td style=\"font-size: 28px; line-height: 1.315789474; margin-top: 4px;\">\n" +
                    "              <span style=\"font-family: Helvetica, Arial, sans-serif; font-weight: 700; color: #ffffff;\">\n" +
                    "                Confirm your email\n" +
                    "              </span>\n" +
                    "            </td>\n" +
                    "          </tr>\n" +
                    "        </table>\n" +
                    "      </td>\n" +
                    "    </tr>\n" +
                    "  </table>\n" +
                    "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse: collapse; max-width: 580px; width: 100% !important;\">\n" +
                    "    <tr>\n" +
                    "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                    "      <td>\n" +
                    "        <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse: collapse;\">\n" +
                    "          <tr>\n" +
                    "            <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                    "          </tr>\n" +
                    "        </table>\n" +
                    "      </td>\n" +
                    "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                    "    </tr>\n" +
                    "  </table>\n" +
                    "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse: collapse; max-width: 580px; width: 100% !important;\">\n" +
                    "    <tr>\n" +
                    "      <td height=\"30\"><br></td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                    "      <td style=\"font-family: Helvetica, Arial, sans-serif; font-size: 19px; line-height: 1.315789474; max-width: 560px;\">\n" +
                    "        <p style=\"margin: 0 0 20px 0; font-size: 19px; line-height: 25px; color: #0b0c0c;\">\n" +
                    "          Hi " + name + ",</p>\n" +
                    "        <p style=\"margin: 0 0 20px 0; font-size: 19px; line-height: 25px; color: #0b0c0c;\">\n" +
                    "          Thank you for registering. Please click on the below link to activate your account:</p>\n" +
                    "        <p style=\"margin: 0 0 20px 0; font-size: 19px; line-height: 25px; color: #0b0c0c;\">\n" +
                    "          <a href=\"" + link + "\" style=\"color: #FFFFFF; text-decoration: none; background-color: #1D70B8; padding: 10px 20px; border-radius: 5px;\">\n" +
                    "            Activate Now</a></p>\n" +
                    "        <p style=\"font-size: 15px; line-height: 20px; color: #0b0c0c;\">Link will expire in 15 minutes. See you soon.</p>\n" +
                    "      </td>\n" +
                    "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "      <td height=\"30\"><br></td>\n" +
                    "    </tr>\n" +
                    "  </table>\n" +
                    "</div>";
        }
    }

