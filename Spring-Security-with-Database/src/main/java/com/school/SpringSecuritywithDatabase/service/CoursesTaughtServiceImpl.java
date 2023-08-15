package com.school.SpringSecuritywithDatabase.service;
import com.school.SpringSecuritywithDatabase.dao.CourseDao;
import com.school.SpringSecuritywithDatabase.dao.CoursesTaughtDao;
import com.school.SpringSecuritywithDatabase.dao.ProfessorDao;
import com.school.SpringSecuritywithDatabase.exc.WrongIdException;
import com.school.SpringSecuritywithDatabase.model.Course;
import com.school.SpringSecuritywithDatabase.model.CoursesTaught;
import com.school.SpringSecuritywithDatabase.model.CoursesTaughtRequest;
import com.school.SpringSecuritywithDatabase.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoursesTaughtServiceImpl implements CoursesTaughtService {
    @Autowired
    private CoursesTaughtDao coursesTaughtDao;

    @Autowired
    private CourseDao coursesDao;

    @Autowired
    private ProfessorDao professorDao;

    @Override
    public CoursesTaught addCoursesTaught(CoursesTaught coursesTaught) throws WrongIdException {
        int professorId = coursesTaught.getProfessor().getId();
        Optional<Professor> optionalProfessor = professorDao.findById(professorId);
        if (optionalProfessor.isPresent()) {
            Professor professor = optionalProfessor.get();
            coursesTaught.setProfessor(professor);
            return this.coursesTaughtDao.save(coursesTaught);
        } else {
            throw new WrongIdException("Professor with ID " + professorId + " doesn't exist.");
        }
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
            Optional<Course> optionalCourses = coursesDao.findById(courseId);
            if (optionalCourses.isPresent()) {
                Course newCourse = optionalCourses.get();
                coursesTaught.setCourse(newCourse);
                return this.coursesTaughtDao.save(coursesTaught);
            } else {
                throw new WrongIdException("Course with ID " + courseId + " doesn't exist.");
            }
        } else {
            throw new WrongIdException("CourseTaught with ID " + id + " doesn't exist.");
        }
    }

    @Override
    public CoursesTaught updateCourseByProfessor(CoursesTaughtRequest request, int professorId, int courseId) {
        CoursesTaught cT = coursesTaughtDao.findCourseTaughtByProfessorAndByCourse(professorId, courseId);
        if (cT != null) {
            int Id = request.getCourseId();
            Optional<Course> optionalCourses = coursesDao.findById(Id);
            if (optionalCourses.isPresent()) {
                Course newCourse = optionalCourses.get();
                cT.setCourse(newCourse);
                return this.coursesTaughtDao.save(cT);
            } else {
                throw new WrongIdException("Course with ID " + courseId + " doesn't exist.");
            }

        } else {
            throw new WrongIdException("CourseTaught with professorId " + professorId + " and courseId " + courseId + " doesn't exist.");

        }
    }
}
