package com.school.SpringSecuritywithDatabase.controller;

import com.school.SpringSecuritywithDatabase.model.generic.GenericEntity;
import com.school.SpringSecuritywithDatabase.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/generic")
public abstract class GenericController<E extends GenericEntity> {
    @Autowired
    private final GenericService<E> genericService;

    public GenericController(GenericService<E> genericService) {
        this.genericService = genericService;
    }

    @PostMapping
    public E create(@RequestBody E entity) {
        return genericService.create(entity);
    }

    @GetMapping("/{id}")
    public E getById(@PathVariable int id) {
        return genericService.getById(id);
    }

    @GetMapping
    public List<E> getAll() {
        return genericService.findAll();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        genericService.delete(id);
        return null;
    }
}
