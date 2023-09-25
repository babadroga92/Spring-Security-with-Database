package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.dao.CoursesTaughtDao;
import com.school.SpringSecuritywithDatabase.dao.ProfessorDao;
import com.school.SpringSecuritywithDatabase.exc.WrongIdException;
import com.school.SpringSecuritywithDatabase.model.Course;
import com.school.SpringSecuritywithDatabase.model.Professor;
import com.school.SpringSecuritywithDatabase.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorServiceImpl extends GenericServiceImpl<Professor> implements ProfessorService{

    private ProfessorDao professorDao;

    private CoursesTaughtDao coursesTaughtDao;
    @Autowired
    public ProfessorServiceImpl(ProfessorDao professorDao, CoursesTaughtDao coursesTaughtDao) {
        this.professorDao = professorDao;
        this.coursesTaughtDao = coursesTaughtDao;
    }


    @Override
    public Professor getById(int id) throws WrongIdException{
        Optional<Professor> optional = this.professorDao.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else {
            throw new WrongIdException("Student with id " + id + " doesn't exist.");
        }
    }

    @Override
    public Professor create(Professor entity) {
        List<Professor> professorList = professorDao.findAll();
        for(Professor professor : professorList){
            if (professor.getName().equals(entity.getName()) && professor.getUser().getId() == entity.getUser().getId()){
                throw new WrongIdException("professor with that name already exist");
            }
        }
        return this.professorDao.save(entity);
    }

    @Override
    public List<Course> findAllCoursesByProfessorId(int professorId) {
        return coursesTaughtDao.findAllCoursesByProfessorId(professorId);
    }

    @Override
    public String delete(int id) throws WrongIdException {
        Optional<Professor> optional = professorDao.findById(id);
        if(optional.isPresent()){
            professorDao.deleteById(id);
            return "Professor deleted";
        }else {
            throw new WrongIdException("Professor with " + id + " doesn't exist.");
        }
    }
}
