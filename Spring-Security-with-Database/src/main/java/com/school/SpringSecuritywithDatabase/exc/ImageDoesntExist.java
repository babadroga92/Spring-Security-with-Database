package com.school.SpringSecuritywithDatabase.exc;

public class ImageDoesntExist extends RuntimeException{
    private String msg;

    public ImageDoesntExist(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
