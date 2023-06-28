package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.dao.CoursesTaughtDao;
import com.school.SpringSecuritywithDatabase.dao.ProfessorDao;
import com.school.SpringSecuritywithDatabase.model.Courses;
import com.school.SpringSecuritywithDatabase.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService{

    private ProfessorDao professorDao;

    private CoursesTaughtDao coursesTaughtDao;
    @Autowired
    public ProfessorServiceImpl(ProfessorDao professorDao, CoursesTaughtDao coursesTaughtDao) {
        this.professorDao = professorDao;
        this.coursesTaughtDao = coursesTaughtDao;
    }

    @Override
    public Professor addProfessor(Professor professor) {
        return this.professorDao.save(professor);
    }

    @Override
    public List<Courses> findAllCoursesByProfessorId(int professorId) {
        return coursesTaughtDao.findAllCoursesByProfessorId(professorId);
    }
}
