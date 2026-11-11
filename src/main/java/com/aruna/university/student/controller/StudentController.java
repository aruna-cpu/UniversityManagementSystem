package com.aruna.university.student.controller;

import com.aruna.university.student.entity.Student;
import com.aruna.university.student.service.StudentService;
import com.aruna.university.dto.StudentTranscriptDto;
import com.aruna.university.grade.service.GradeService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService service;
    private final GradeService gradeService;

    public StudentController(StudentService service, GradeService gradeService) {
        this.service = service;
        this.gradeService = gradeService;
    }

    @GetMapping
    public List<Student> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Student one(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@Valid @RequestBody Student student) {
        return service.create(student);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @Valid @RequestBody Student student) {
        return service.update(id, student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}/transcript")
    public StudentTranscriptDto transcript(@PathVariable Long id) {
        return gradeService.transcript(id);
    }
}
