package com.aruna.university.grade.repository;

import com.aruna.university.grade.entity.Grade;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudentId(Long studentId);
}
