package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.model.registration.token.ConfirmationToken;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenService {
    Optional<ConfirmationToken> findByToken(String token);
    int updateConfirmedAt(String token,
                          LocalDateTime confirmedAt);
    void saveConfirmationToken(ConfirmationToken token);
    int setConfirmedAt(String token);
    void deleteById(int id);
}
