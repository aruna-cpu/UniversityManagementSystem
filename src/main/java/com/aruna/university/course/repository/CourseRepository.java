package com.aruna.university.course.repository;

import com.aruna.university.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByCourseCodeIgnoreCase(String courseCode);
}
