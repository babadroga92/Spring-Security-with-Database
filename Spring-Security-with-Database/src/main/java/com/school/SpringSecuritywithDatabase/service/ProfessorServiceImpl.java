package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.dao.ProfessorDao;
import com.school.SpringSecuritywithDatabase.model.Professor;
import org.springframework.stereotype.Service;

@Service
public class ProfessorServiceImpl implements ProfessorService{

    private ProfessorDao professorDao;

    public ProfessorServiceImpl(ProfessorDao professorDao) {
        this.professorDao = professorDao;
    }

    @Override
    public Professor addProfessor(Professor professor) {
        return this.professorDao.save(professor);
    }
}
