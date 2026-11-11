package com.aruna.university.grade.controller;

import com.aruna.university.grade.entity.Grade;
import com.aruna.university.grade.service.GradeService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grades")
public class GradeController {
    private final GradeService service;

    public GradeController(GradeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Grade> all() {
        return service.all();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Grade assign(@Valid @RequestBody Grade grade) {
        return service.assign(grade);
    }

    @GetMapping("/student/{id}")
    public List<Grade> studentGrades(@PathVariable Long id) {
        return service.gradesForStudent(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
