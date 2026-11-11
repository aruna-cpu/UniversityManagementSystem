package com.aruna.university.grade.service;

import com.aruna.university.grade.entity.Grade;
import com.aruna.university.grade.repository.GradeRepository;
import com.aruna.university.student.service.StudentService;
import com.aruna.university.course.service.CourseService;
import com.aruna.university.course.entity.Course;
import com.aruna.university.dto.StudentTranscriptDto;
import com.aruna.university.dto.StudentTranscriptDto.CourseGradeDto;
import com.aruna.university.exception.ApiException;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GradeService {
    private final GradeRepository gradeRepository;
    private final StudentService students;
    private final CourseService courses;

    public GradeService(GradeRepository gradeRepository, StudentService students, CourseService courses) {
        this.gradeRepository = gradeRepository;
        this.students = students;
        this.courses = courses;
    }

    @Transactional(transactionManager = "gradeTransactionManager")
    public Grade assign(Grade grade) {
        students.findById(grade.getStudentId());
        courses.findById(grade.getCourseId());
        grade.setId(null);
        grade.setLetterGrade(letter(grade.getScore()));
        return gradeRepository.save(grade);
    }

    @Transactional(readOnly = true, transactionManager = "gradeTransactionManager")
    public List<Grade> gradesForStudent(Long studentId) {
        students.findById(studentId);
        return gradeRepository.findByStudentId(studentId);
    }

    @Transactional(readOnly = true, transactionManager = "gradeTransactionManager")
    public List<Grade> all() {
        return gradeRepository.findAll();
    }

    @Transactional(transactionManager = "gradeTransactionManager")
    public void delete(Long id) {
        if (!gradeRepository.existsById(id)) throw new ApiException(HttpStatus.NOT_FOUND, "Grade not found: " + id);
        gradeRepository.deleteById(id);
    }

    public StudentTranscriptDto transcript(Long studentId) {
        var student = students.findById(studentId);
        List<Grade> grades = gradeRepository.findByStudentId(studentId);
        List<CourseGradeDto> details = new ArrayList<>();
        double total = 0;
        for (Grade g : grades) {
            Course c = courses.findById(g.getCourseId());
            details.add(new CourseGradeDto(c.getId(), c.getCourseName(), c.getCourseCode(), g.getScore(), g.getLetterGrade()));
            total += g.getScore();
        }
        Double average = grades.isEmpty() ? null : Math.round((total / grades.size()) * 100.0) / 100.0;
        return new StudentTranscriptDto(student.getId(), student.getFirstName(), student.getLastName(), student.getEmail(), student.getMajor(), details, average);
    }

    private String letter(Integer score) {
        if (score >= 95) return "A";
        if (score >= 90) return "A-";
        if (score >= 85) return "B+";
        if (score >= 80) return "B";
        if (score >= 75) return "B-";
        if (score >= 70) return "C+";
        if (score >= 65) return "C";
        if (score >= 60) return "C-";
        if (score >= 50) return "D";
        return "F";
    }
}
