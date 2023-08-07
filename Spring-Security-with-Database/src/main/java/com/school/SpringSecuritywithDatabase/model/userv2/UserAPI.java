package com.school.SpringSecuritywithDatabase.model.userv2;

public class UserAPI {
    private int id;

    private String gender;
    private Name name;

    public UserAPI(int id, String gender, Name name) {
        this.id = id;
        this.gender = gender;
        this.name = name;
    }

    public UserAPI() {
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
