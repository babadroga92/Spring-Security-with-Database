package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.dao.CoursesDao;
import com.school.SpringSecuritywithDatabase.exc.WrongIdException;
import com.school.SpringSecuritywithDatabase.model.Courses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursesServiceImpl implements CoursesService{
    @Autowired
    private CoursesDao coursesDao;

    @Override
    public Courses addCourse(Courses courses) {
        return this.coursesDao.save(courses);
    }

    @Override
    public List<Courses> findAll() {
        return this.coursesDao.findAll();
    }

    @Override
    public Courses findById(int id) throws WrongIdException {
        Optional<Courses> optional = this.coursesDao.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        else {
            throw new WrongIdException("Course with " + id + " doesnt exist");
        }
    }
}
