package com.school.SpringSecuritywithDatabase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.school.SpringSecuritywithDatabase.model.generic.GenericEntity;
import com.school.SpringSecuritywithDatabase.view.View;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "professor")
public class Professor extends GenericEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
    @JsonView(View.ShowMinimal.class)
    private String name;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToMany
    @JoinTable(
            name = "courses_taught",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @JsonIgnore
    private List<Course> course;

    public Professor() {
    }

    public Professor(String name, User user, List<Course> course) {
        this.name = name;
        this.user = user;
        this.course = course;
    }

    public Professor(String name) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Course> getCourses() {
        return course;
    }

    public void setCourses(List<Course> course) {
        this.course = course;
    }
}
