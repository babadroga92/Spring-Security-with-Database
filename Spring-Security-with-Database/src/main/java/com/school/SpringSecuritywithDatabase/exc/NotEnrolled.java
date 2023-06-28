package com.school.SpringSecuritywithDatabase.exc;

public class NotEnrolled extends RuntimeException{
    private String msg;

    public NotEnrolled(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
