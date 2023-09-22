package com.school.SpringSecuritywithDatabase.model;



import com.fasterxml.jackson.annotation.JsonView;
import com.school.SpringSecuritywithDatabase.view.View;

import javax.persistence.*;

@Entity
@Table(name = "courses_taught")
public class CoursesTaught {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.ShowMinimal.class)
    private int id;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    //@JsonIgnore
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "course_id")
    //@JsonIgnore
    @JsonView(View.ShowMinimal.class)
    private Course course;

    public CoursesTaught() {
    }

    public CoursesTaught(Professor professor, Course course) {
        this.professor = professor;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
