package com.school.SpringSecuritywithDatabase.model.registration;

import com.school.SpringSecuritywithDatabase.dao.StudentDao;
import com.school.SpringSecuritywithDatabase.model.Student;
import com.school.SpringSecuritywithDatabase.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentRegistrationService {
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private StudentDao studentDao;

    public StudentRegistrationService() {
    }

    public StudentRegistrationService(StudentServiceImpl studentService, StudentDao studentDao) {
        this.studentService = studentService;
        this.studentDao = studentDao;
    }

    public void register(StudentRegistrationRequest request){
        studentService.addStudent(new Student(
                request.getName()
        ));
    }
}
