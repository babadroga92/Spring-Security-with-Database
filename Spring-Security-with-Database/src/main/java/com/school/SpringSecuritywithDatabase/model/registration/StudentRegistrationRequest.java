package com.school.SpringSecuritywithDatabase.model.registration;

public class StudentRegistrationRequest {
    private String name;

    public StudentRegistrationRequest() {
    }

    public StudentRegistrationRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudentRegistrationRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
