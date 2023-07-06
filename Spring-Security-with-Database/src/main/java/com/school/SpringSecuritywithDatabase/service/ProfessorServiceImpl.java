package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.dao.CoursesTaughtDao;
import com.school.SpringSecuritywithDatabase.dao.ProfessorDao;
import com.school.SpringSecuritywithDatabase.exc.WrongIdException;
import com.school.SpringSecuritywithDatabase.model.Course;
import com.school.SpringSecuritywithDatabase.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Professor findById(int id)throws WrongIdException {
        Optional<Professor> optional = professorDao.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else {
            throw new WrongIdException("Professor with id  " + id + " doesn't exist.");
        }

    }

    @Override
    public List<Course> findAllCoursesByProfessorId(int professorId) {
        return coursesTaughtDao.findAllCoursesByProfessorId(professorId);
    }


}
