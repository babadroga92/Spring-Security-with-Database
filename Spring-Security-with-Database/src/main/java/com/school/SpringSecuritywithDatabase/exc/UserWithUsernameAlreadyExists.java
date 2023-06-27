package com.school.SpringSecuritywithDatabase.exc;

public class UserWithUsernameAlreadyExists extends RuntimeException{
    private String msg;

    public UserWithUsernameAlreadyExists(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
