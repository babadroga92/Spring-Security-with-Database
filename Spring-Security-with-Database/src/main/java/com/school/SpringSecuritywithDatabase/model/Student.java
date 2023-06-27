package com.school.SpringSecuritywithDatabase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.school.SpringSecuritywithDatabase.enums.Grade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "courses_taken",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @JsonIgnore // ZASTO MI TREBA OVO DA BIH VRATIO LISTU SVIH UCENIKA??
    private List<Courses> courses;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    public Student() {
    }

    public Student(String name, List<Courses> courses, User user) {
        this.name = name;
        this.courses = courses;
        this.user = user;
    }

    public Student(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Courses> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
