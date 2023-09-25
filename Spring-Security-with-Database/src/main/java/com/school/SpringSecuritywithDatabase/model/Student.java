package com.school.SpringSecuritywithDatabase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.school.SpringSecuritywithDatabase.model.generic.GenericEntity;
import com.school.SpringSecuritywithDatabase.view.View;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class Student extends GenericEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    @JsonView(View.ShowMinimal.class)
    private int id;
    @Column(name = "name")
    @JsonView(View.ShowMinimal.class)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "courses_taken",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @JsonIgnore
    @JsonView(View.ShowMinimal.class)
    private List<Course> course;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    public Student() {
    }

    public Student(String name, List<Course> course, User user) {
        this.name = name;
        this.course = course;
        this.user = user;
    }

    public Student(String name) {
        this.name = name;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Course> getCourses() {
        return course;
    }

    public void setCourses(List<Course> course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
