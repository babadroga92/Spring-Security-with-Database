package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.dao.CoursesTakenDao;
import com.school.SpringSecuritywithDatabase.model.CoursesTaken;
import com.school.SpringSecuritywithDatabase.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CoursesTakenServiceImpl implements CoursesTakenService{
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

}
