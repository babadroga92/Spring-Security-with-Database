package com.school.SpringSecuritywithDatabase.exc;

public class EmailAlreadyInUse extends RuntimeException{

    private String message;


    public EmailAlreadyInUse(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
