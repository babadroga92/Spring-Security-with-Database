package com.school.SpringSecuritywithDatabase.model;



import javax.persistence.*;

@Entity
@Table(name = "courses_taught")
public class CoursesTaught {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Courses course;

    public CoursesTaught() {
    }

    public CoursesTaught(Professor professor, Courses course) {
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

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }
}
