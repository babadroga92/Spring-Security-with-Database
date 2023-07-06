package com.school.SpringSecuritywithDatabase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.school.SpringSecuritywithDatabase.view.View;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.ShowMinimal.class)
    private int id;
    @NonNull
    @JsonView(View.ShowMinimal.class)
    private String name;

    @ManyToMany(mappedBy = "course")
    @JsonIgnore
    private List<Student> student;
    @ManyToMany(mappedBy = "course")
    @JsonIgnore
    private List<Professor> professors;


    public Course() {
    }

    public Course(@NonNull String name, List<Student> student, List<Professor> professors) {

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
