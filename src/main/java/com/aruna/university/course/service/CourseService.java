package com.aruna.university.course.service;

import com.aruna.university.course.entity.Course;
import com.aruna.university.course.repository.CourseRepository;
import com.aruna.university.exception.ApiException;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true, transactionManager = "courseTransactionManager")
    public List<Course> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true, transactionManager = "courseTransactionManager")
    public Course findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Course not found: " + id));
    }

    @Transactional(transactionManager = "courseTransactionManager")
    public Course create(Course c) {
        if (repository.existsByCourseCodeIgnoreCase(c.getCourseCode()))
            throw new ApiException(HttpStatus.CONFLICT, "Course code already exists.");
        c.setId(null);
        return repository.save(c);
    }

    @Transactional(transactionManager = "courseTransactionManager")
    public Course update(Long id, Course c) {
        Course old = findById(id);
        if (!old.getCourseCode().equalsIgnoreCase(c.getCourseCode()) && repository.existsByCourseCodeIgnoreCase(c.getCourseCode()))
            throw new ApiException(HttpStatus.CONFLICT, "Course code already exists.");
        old.setCourseName(c.getCourseName());
        old.setCourseCode(c.getCourseCode());
        old.setCredits(c.getCredits());
        old.setInstructor(c.getInstructor());
        return repository.save(old);
    }

    @Transactional(transactionManager = "courseTransactionManager")
    public void delete(Long id) {
        if (!repository.existsById(id)) throw new ApiException(HttpStatus.NOT_FOUND, "Course not found: " + id);
        repository.deleteById(id);
    }
}
