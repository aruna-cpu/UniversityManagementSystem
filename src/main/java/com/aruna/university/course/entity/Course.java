package com.aruna.university.course.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 160)
    @Column(name = "course_name", nullable = false)
    private String courseName;
    @NotBlank
    @Size(max = 30)
    @Column(name = "course_code", nullable = false, unique = true)
    private String courseCode;
    @NotNull
    @Min(1)
    @Max(30)
    @Column(nullable = false)
    private Integer credits;
    @NotBlank
    @Size(max = 120)
    @Column(nullable = false)
    private String instructor;

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
}
