package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.model.Course;
import com.school.SpringSecuritywithDatabase.model.Professor;
import com.school.SpringSecuritywithDatabase.model.Student;

import java.util.List;

public interface ProfessorService extends GenericService<Professor>{
//    void addProfessor(Professor professor);
//
//    Professor findById(int id);

    List<Course> findAllCoursesByProfessorId(int professorId);

}
