package com.school.SpringSecuritywithDatabase.service;
import com.school.SpringSecuritywithDatabase.dao.CoursesDao;
import com.school.SpringSecuritywithDatabase.dao.CoursesTaughtDao;
import com.school.SpringSecuritywithDatabase.exc.WrongIdException;
import com.school.SpringSecuritywithDatabase.model.Courses;
import com.school.SpringSecuritywithDatabase.model.CoursesTaught;
import com.school.SpringSecuritywithDatabase.model.CoursesTaughtRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoursesTaughtServiceImpl implements CoursesTaughtService {
    @Autowired
    private CoursesTaughtDao coursesTaughtDao;

    @Autowired
    private CoursesDao coursesDao;

    @Override
    public CoursesTaught addCoursesTaught(CoursesTaught coursesTaught) {
        return this.coursesTaughtDao.save(coursesTaught);
    }

    @Override
    public CoursesTaught findById(int id) throws WrongIdException {
        Optional<CoursesTaught> optional = coursesTaughtDao.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new WrongIdException("CoursesTaught with id " + id + " doesn't exist.");
        }
    }

    @Override
    public CoursesTaught updateCourseId(CoursesTaughtRequest request, int id) {
        Optional<CoursesTaught> optionalCoursesTaught = coursesTaughtDao.findById(id);
        if (optionalCoursesTaught.isPresent()) {
            CoursesTaught coursesTaught = optionalCoursesTaught.get();
            int courseId = request.getCourseId();
            Optional<Courses> optionalCourses = coursesDao.findById(courseId);
            if (optionalCourses.isPresent()) {
                Courses newCourse = optionalCourses.get();
                coursesTaught.setCourse(newCourse);
                return this.coursesTaughtDao.save(coursesTaught);
            } else {
                throw new WrongIdException("Course with ID " + courseId + " doesn't exist.");
            }
        } else {
            throw new WrongIdException("CourseTaught with ID " + id + " doesn't exist.");
        }
    }
}
