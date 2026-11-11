package com.aruna.university.student.service;

import com.aruna.university.student.entity.Student;
import com.aruna.university.student.repository.StudentRepository;
import com.aruna.university.exception.ApiException;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true, transactionManager = "studentTransactionManager")
    public List<Student> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true, transactionManager = "studentTransactionManager")
    public Student findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Student not found: " + id));
    }

    @Transactional(transactionManager = "studentTransactionManager")
    public Student create(Student student) {
        if (repository.existsByEmailIgnoreCase(student.getEmail()))
            throw new ApiException(HttpStatus.CONFLICT, "Email already exists.");
        student.setId(null);
        return repository.save(student);
    }

    @Transactional(transactionManager = "studentTransactionManager")
    public Student update(Long id, Student input) {
        Student current = findById(id);
        if (!current.getEmail().equalsIgnoreCase(input.getEmail()) && repository.existsByEmailIgnoreCase(input.getEmail()))
            throw new ApiException(HttpStatus.CONFLICT, "Email already exists.");
        current.setFirstName(input.getFirstName());
        current.setLastName(input.getLastName());
        current.setEmail(input.getEmail());
        current.setMajor(input.getMajor());
        current.setEnrollmentYear(input.getEnrollmentYear());
        return repository.save(current);
    }

    @Transactional(transactionManager = "studentTransactionManager")
    public void delete(Long id) {
        if (!repository.existsById(id)) throw new ApiException(HttpStatus.NOT_FOUND, "Student not found: " + id);
        repository.deleteById(id);
    }
}
