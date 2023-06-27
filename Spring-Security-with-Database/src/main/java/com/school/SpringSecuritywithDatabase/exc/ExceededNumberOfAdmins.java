package com.school.SpringSecuritywithDatabase.exc;

public class ExceededNumberOfAdmins extends RuntimeException{
    private String msg;

    public ExceededNumberOfAdmins(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
