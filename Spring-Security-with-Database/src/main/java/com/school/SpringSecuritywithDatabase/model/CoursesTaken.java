package com.school.SpringSecuritywithDatabase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.school.SpringSecuritywithDatabase.enums.Grade;
import com.school.SpringSecuritywithDatabase.view.View;

import javax.persistence.*;

@Entity
@Table(name = "courses_taken")
public class CoursesTaken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.ShowMinimal.class)
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    //@JsonIgnore   Without this i can create an object CoursesTaken in Postman with all fields included
    @JsonView(View.ShowMinimal.class)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    //@JsonIgnore Without this i can create an object CoursesTaken in Postman with all fields included
    @JsonView(View.ShowMinimal.class)
    private Courses course;

    @Column(name = "grade")
    @Enumerated(EnumType.STRING)
    private Grade grade;

    public CoursesTaken() {
    }

    public CoursesTaken(Student student, Courses course, Grade grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
