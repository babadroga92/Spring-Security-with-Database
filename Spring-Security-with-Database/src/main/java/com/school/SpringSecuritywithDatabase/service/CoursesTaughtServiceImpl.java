package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.dao.CoursesTaughtDao;
import com.school.SpringSecuritywithDatabase.model.CoursesTaught;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoursesTaughtServiceImpl implements CoursesTaughtService {
    @Autowired
    private CoursesTaughtDao coursesTaughtDao;
    @Override
    public CoursesTaught addCoursesTaught(CoursesTaught coursesTaught) {
        return this.coursesTaughtDao.save(coursesTaught);
    }
}
