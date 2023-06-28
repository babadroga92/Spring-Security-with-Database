package com.school.SpringSecuritywithDatabase.controller;
import com.school.SpringSecuritywithDatabase.exc.WrongIdException;
import com.school.SpringSecuritywithDatabase.model.CoursesTaught;
import com.school.SpringSecuritywithDatabase.model.CoursesTaughtRequest;
import com.school.SpringSecuritywithDatabase.service.CoursesTaughtServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coursesTaught")
public class CoursesTaughtController {

    @Autowired
    private CoursesTaughtServiceImpl coursesTaughtServiceImpl;

    @PostMapping("/add")
    public CoursesTaught coursesTaught(@RequestBody CoursesTaught coursesTaught){
        return this.coursesTaughtServiceImpl.addCoursesTaught(coursesTaught);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CoursesTaught> findById(@PathVariable int id) throws WrongIdException {
        return new ResponseEntity<>(coursesTaughtServiceImpl.findById(id), HttpStatus.OK);
    }
    /*
    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable int id) throws WrongIdException {
    return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }
     */

    @PutMapping("/update/{id}")
    public CoursesTaught updateCourseId(@PathVariable("id")int id, @RequestBody CoursesTaughtRequest request) throws WrongIdException{
       return coursesTaughtServiceImpl.updateCourseId(request,id);
    }
    }
