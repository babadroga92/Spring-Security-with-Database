package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.model.Courses;
import com.school.SpringSecuritywithDatabase.model.Professor;

import java.util.List;

public interface ProfessorService {
    Professor addProfessor(Professor professor);

    List<Courses> findAllCoursesByProfessorId(int professorId);

}
