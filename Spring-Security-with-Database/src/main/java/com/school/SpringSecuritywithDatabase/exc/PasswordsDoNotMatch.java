package com.school.SpringSecuritywithDatabase.exc;

public class PasswordsDoNotMatch extends RuntimeException{

    private String msg;

    public PasswordsDoNotMatch(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
