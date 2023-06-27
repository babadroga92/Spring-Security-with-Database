package com.school.SpringSecuritywithDatabase.controller;

import com.school.SpringSecuritywithDatabase.model.Professor;
import com.school.SpringSecuritywithDatabase.service.ProfessorServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
