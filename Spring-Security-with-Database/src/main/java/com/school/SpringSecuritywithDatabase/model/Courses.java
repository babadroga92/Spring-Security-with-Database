package com.school.SpringSecuritywithDatabase.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Since its AUTO, I don't need this field added in a constructor
    private int id;
    @NonNull
    private String name;

    public Courses() {
    }

    public Courses(@NonNull String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }
}
