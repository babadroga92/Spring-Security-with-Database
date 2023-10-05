package com.school.SpringSecuritywithDatabase.controller;
import com.fasterxml.jackson.annotation.JsonView;
import com.school.SpringSecuritywithDatabase.exc.WrongIdException;
import com.school.SpringSecuritywithDatabase.model.Course;
import com.school.SpringSecuritywithDatabase.model.Professor;
import com.school.SpringSecuritywithDatabase.model.Student;
import com.school.SpringSecuritywithDatabase.service.ProfessorServiceImpl;
import com.school.SpringSecuritywithDatabase.view.View;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController extends GenericController<Professor>{

    private ProfessorServiceImpl professorServiceImpl;

    public ProfessorController(ProfessorServiceImpl professorServiceImpl) {
        super(professorServiceImpl);
        this.professorServiceImpl = professorServiceImpl;
    }
    @PostMapping("/registration")
    @JsonView(View.ShowMinimal.class)
    public String addProfessor(@RequestBody @Valid Professor professor){
        super.create(professor);
        return "Professor added";
    }
    @GetMapping("/{id}/listOfCourses")
    public List<Course> findAllCoursesByProfessorId(@PathVariable int id){
        return professorServiceImpl.findAllCoursesByProfessorId(id);
    }

    @GetMapping("/{id}/professor-id")
    @JsonView(View.ShowMinimal.class)
    public ResponseEntity<Professor> findById(@PathVariable int id) throws WrongIdException {
        return new ResponseEntity<>(professorServiceImpl.getById(id), HttpStatus.OK);
    }
    @Override
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        return super.delete(id);
    }
}
