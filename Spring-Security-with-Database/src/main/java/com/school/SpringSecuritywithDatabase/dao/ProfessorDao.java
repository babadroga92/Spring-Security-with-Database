package com.school.SpringSecuritywithDatabase.dao;

import com.school.SpringSecuritywithDatabase.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorDao extends JpaRepository<Professor, Integer> {
}
