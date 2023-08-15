package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.dao.CourseDao;
import com.school.SpringSecuritywithDatabase.exc.WrongIdException;
import com.school.SpringSecuritywithDatabase.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursesServiceImpl implements CoursesService{
    @Autowired
    private CourseDao courseDao;

    public CoursesServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public Course addCourse(Course course) {
        return this.courseDao.save(course);
    }

    @Override
    public List<Course> findAll() {
        return this.courseDao.findAll();
    }

    @Override
    public Course findById(int id) throws WrongIdException {
        Optional<Course> optional = this.courseDao.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        else {
            throw new WrongIdException("Course with " + id + " doesnt exist");
        }
    }


}
