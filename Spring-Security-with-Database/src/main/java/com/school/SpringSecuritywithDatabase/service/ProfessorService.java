package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.model.Course;
import com.school.SpringSecuritywithDatabase.model.Professor;

import java.util.List;

public interface ProfessorService {
    Professor addProfessor(Professor professor);

    Professor findById(int id);

    List<Course> findAllCoursesByProfessorId(int professorId);

}
