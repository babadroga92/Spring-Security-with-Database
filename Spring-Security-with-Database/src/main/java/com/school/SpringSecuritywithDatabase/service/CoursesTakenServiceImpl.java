package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.dao.CoursesTakenDao;
import com.school.SpringSecuritywithDatabase.exc.WrongIdException;
import com.school.SpringSecuritywithDatabase.model.CoursesTaken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CoursesTakenServiceImpl implements CoursesTakenService {
    @Autowired
    private CoursesTakenDao coursesTakenDao;


    @Override
    public CoursesTaken addCoursesTaken(CoursesTaken coursesTaken) {
        return this.coursesTakenDao.save(coursesTaken);
    }

    @Override
    public List<CoursesTaken> findAll() {
        return coursesTakenDao.findAll();
    }

    @Override
    public CoursesTaken findById(int id) throws WrongIdException {
        Optional<CoursesTaken> optional = coursesTakenDao.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new WrongIdException("CourseTaken with id " + id + " doesnt exist");

        }
    }

    @Override
    public String deleteById(int id) {
        Optional<CoursesTaken> optional = coursesTakenDao.findById(id);
        if (optional.isPresent()) {
            coursesTakenDao.delete(optional.get());
            return "Deletion successful";
        } else {
            throw new WrongIdException("CourseTaken with id " + id + " doesn't exist");
        }
    }
}
