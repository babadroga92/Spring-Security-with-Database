package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.dao.CoursesTakenDao;
import com.school.SpringSecuritywithDatabase.dao.StudentDao;
import com.school.SpringSecuritywithDatabase.exc.NotEnrolled;
import com.school.SpringSecuritywithDatabase.exc.WrongIdException;
import com.school.SpringSecuritywithDatabase.model.Course;

import com.school.SpringSecuritywithDatabase.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    private StudentDao studentDao;
    private CoursesTakenDao coursesTakenDao;
    @Autowired
    public StudentServiceImpl(StudentDao studentDao, CoursesTakenDao coursesTakenDao) {
        this.studentDao = studentDao;
        this.coursesTakenDao = coursesTakenDao;
    }

    @Override
    public Student addStudent(Student student) {
        return this.studentDao.save(student);
    }

    @Override
    public List<Student> findAll() {
        return this.studentDao.findAll();
    }

    @Override
    public String deleteById(int id)throws WrongIdException {
        Optional<Student> optional = studentDao.findById(id);
        if(optional.isPresent()){
            studentDao.deleteById(id);
            return "student deleted";
        }else {
            throw new WrongIdException("Student with " + id + " doesn't exist.");
        }
    }

    @Override
    public Student findById(int id) throws WrongIdException{
        Optional<Student> optional = this.studentDao.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else {
            throw new WrongIdException("Student with id " + id + " doesn't exist.");
        }
    }



    @Override
    public List<Course> findAllCoursesByStudentId(int id)throws NotEnrolled {
        Optional<Student> student = studentDao.findById(id);
        if (!student.isPresent()) {
            throw new WrongIdException("Student with id " + id + " doesn't exist.");
        }
        List<Course> course = coursesTakenDao.findAllCoursesByStudentId(id);
        if(course.isEmpty()){
            throw new NotEnrolled("Student with id " + id + " is not enrolled to any class." );
        }
        return course;
    }

    @Override
    public Student updateStudentsName(int id, Student student) {
        Student studentDb = findById(id);
        studentDb.setName(student.getName());
        return studentDao.save(studentDb);
    }
    @Override
    public void findAllStudentsTWithoutUserId() {
        List<Student> studentsWithoutUser = studentDao.findAllStudentsTWithoutUserId();
        logger.info("Start of the process of eliminating all the students without associated user.");
        studentDao.deleteAll(studentsWithoutUser);
    }
    @Scheduled(cron = "${student-deletion-cron}")
    @Override
    public void findAllStudentsTWithoutUserIdCustomScheduling() {
        List<Student> studentsWithoutUser = studentDao.findAllStudentsTWithoutUserId();
        logger.info("Start of the process of eliminating all the students without associated user.");
        studentDao.deleteAll(studentsWithoutUser);
    }
}
