package com.school.SpringSecuritywithDatabase.model.userApiGet;

public class UserAPI {

    private String gender;
    private Name name;

    public UserAPI( String gender, Name name) {
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

}
