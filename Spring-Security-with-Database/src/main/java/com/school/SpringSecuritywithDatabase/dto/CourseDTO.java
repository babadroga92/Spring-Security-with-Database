package com.school.SpringSecuritywithDatabase.dto;

import com.school.SpringSecuritywithDatabase.enums.Grade;

public class CourseDTO {
    private String subject;
    private Grade grade;
    private String name;

    public CourseDTO() {
    }

    public CourseDTO(String subject, Grade grade, String name) {
        this.subject = subject;
        this.grade = grade;
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
