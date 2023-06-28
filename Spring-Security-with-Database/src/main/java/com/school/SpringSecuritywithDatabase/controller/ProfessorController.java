package com.school.SpringSecuritywithDatabase.controller;

import com.school.SpringSecuritywithDatabase.model.Courses;
import com.school.SpringSecuritywithDatabase.model.Professor;
import com.school.SpringSecuritywithDatabase.service.ProfessorServiceImpl;
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
    public List<Courses> findAllCoursesByProfessorId(@PathVariable int id){
        return professorServiceImpl.findAllCoursesByProfessorId(id);
    }
}
