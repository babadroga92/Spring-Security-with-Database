package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.dao.ConfirmationTokenDao;
import com.school.SpringSecuritywithDatabase.exc.WrongIdException;
import com.school.SpringSecuritywithDatabase.model.registration.token.ConfirmationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService{
    @Autowired
    private ConfirmationTokenDao confirmationTokenDao;

    public ConfirmationTokenServiceImpl(ConfirmationTokenDao confirmationTokenDao) {
        this.confirmationTokenDao = confirmationTokenDao;
    }

    @Override
    public Optional<ConfirmationToken> findByToken(String token) {
        return confirmationTokenDao.findByToken(token);
    }

    @Override
    public int updateConfirmedAt(String token, LocalDateTime confirmedAt) {
        return confirmationTokenDao.updateConfirmedAt(token, LocalDateTime.now());
    }

    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenDao.save(token);
    }

    @Override
    public int setConfirmedAt(String token) {
        return confirmationTokenDao.updateConfirmedAt(token, LocalDateTime.now());
    }

    @Override
    public void deleteById(int id) {
        Optional<ConfirmationToken> optional = confirmationTokenDao.findById(id);
        if(optional.isPresent()){
            confirmationTokenDao.deleteById(id);
        }
        else {
            throw new WrongIdException("Token with id " + id + " doesn't exist");
        }
    }

}
