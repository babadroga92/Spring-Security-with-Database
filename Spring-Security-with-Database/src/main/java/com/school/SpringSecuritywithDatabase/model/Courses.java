package com.school.SpringSecuritywithDatabase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Since its AUTO, I don't need this field added in a constructor
    private int id;
    @NonNull
    private String name;

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private List<Student> student;
    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private List<Professor> professors;


    public Courses() {
    }

    public Courses(@NonNull String name, List<Student> student, List<Professor> professors) {

        this.name = name;
        this.student = student;
        this.professors = professors;
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

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }
}
