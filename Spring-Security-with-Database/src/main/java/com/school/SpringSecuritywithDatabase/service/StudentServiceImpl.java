package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.dao.CoursesTakenDao;
import com.school.SpringSecuritywithDatabase.dao.StudentDao;
import com.school.SpringSecuritywithDatabase.model.CoursesTaken;
import com.school.SpringSecuritywithDatabase.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

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
    public String deleteById(int id) {
        Optional<Student> optional = studentDao.findById(id);
        if(optional.isPresent()){
            studentDao.deleteById(id);
            return "User deleted";
        }
        return null;
    }

//    @Override
//    public List<CoursesTaken> findByStudent(int studentId) {
//        return this.coursesTakenDao.findByStudent(studentId);
//    }
}
