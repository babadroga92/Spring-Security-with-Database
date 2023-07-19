package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.dao.CoursesTakenDao;
import com.school.SpringSecuritywithDatabase.dto.CourseDTO;
import com.school.SpringSecuritywithDatabase.dto.StudentDTO;
import com.school.SpringSecuritywithDatabase.enums.Grade;
import com.school.SpringSecuritywithDatabase.exc.WrongIdException;
import com.school.SpringSecuritywithDatabase.model.Course;
import com.school.SpringSecuritywithDatabase.model.CoursesTaken;
import com.school.SpringSecuritywithDatabase.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Override
    public List<String> findAllCoursesByStudentName(String name) {
        if (name == null) {
            throw new UsernameNotFoundException("Student not found");
        }
            return this.coursesTakenDao.findAllCoursesByStudentName(name);
        }

    @Override
    public List<CourseDTO> findAllCoursesByStudentNameDto(String name) {
        return this.coursesTakenDao.findAllCoursesByStudentNameDto(name);
    }

    @Override
    public Integer findNumberOfCourseTakenByStudents(String name) {
        if (name == null) {
            throw new UsernameNotFoundException("Subject not found");
        }
        return this.coursesTakenDao.findNumberOfCourseTakenByStudents(name);
    }

    @Override
    public List<StudentDTO> findAllStudents(Grade grade, String course) {
        return this.coursesTakenDao.findAllStudents(grade, course);
    }
}
