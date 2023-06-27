package com.school.SpringSecuritywithDatabase.exc;

public class WrongIdException extends RuntimeException{

    private String msg;

    public WrongIdException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
