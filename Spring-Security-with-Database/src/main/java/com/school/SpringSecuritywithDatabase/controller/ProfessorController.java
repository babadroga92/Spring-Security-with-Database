package com.school.SpringSecuritywithDatabase.controller;
import com.school.SpringSecuritywithDatabase.exc.WrongIdException;
import com.school.SpringSecuritywithDatabase.model.Course;
import com.school.SpringSecuritywithDatabase.model.Professor;
import com.school.SpringSecuritywithDatabase.service.ProfessorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    private ProfessorServiceImpl professorServiceImpl;

    public ProfessorController(ProfessorServiceImpl professorServiceImpl) {
        this.professorServiceImpl = professorServiceImpl;
    }
    @PostMapping
    public String addProfessor(@RequestBody @Valid Professor professor){
        professorServiceImpl.addProfessor(professor);
        return "Professor added";
    }
    @GetMapping("/{id}/listOfCourses")
    public List<Course> findAllCoursesByProfessorId(@PathVariable int id){
        return professorServiceImpl.findAllCoursesByProfessorId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> findById(@PathVariable int id) throws WrongIdException {
        return new ResponseEntity<>(professorServiceImpl.findById(id), HttpStatus.OK);
    }
}
