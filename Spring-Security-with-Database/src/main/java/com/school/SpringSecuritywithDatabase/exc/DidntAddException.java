package com.school.SpringSecuritywithDatabase.exc;

public class DidntAddException extends RuntimeException {
    private String msg;

    public DidntAddException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
