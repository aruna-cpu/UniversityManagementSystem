package com.aruna.university.dto;

import java.util.List;

public class StudentTranscriptDto {
    private Long studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String major;
    private List<CourseGradeDto> courses;
    private Double averageScore;

    public StudentTranscriptDto(Long studentId, String firstName, String lastName, String email, String major, List<CourseGradeDto> courses, Double averageScore) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.major = major;
        this.courses = courses;
        this.averageScore = averageScore;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getMajor() {
        return major;
    }

    public List<CourseGradeDto> getCourses() {
        return courses;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public static class CourseGradeDto {
        private Long courseId;
        private String courseName;
        private String courseCode;
        private Integer score;
        private String letterGrade;

        public CourseGradeDto(Long courseId, String courseName, String courseCode, Integer score, String letterGrade) {
            this.courseId = courseId;
            this.courseName = courseName;
            this.courseCode = courseCode;
            this.score = score;
            this.letterGrade = letterGrade;
        }

        public Long getCourseId() {
            return courseId;
        }

        public String getCourseName() {
            return courseName;
        }

        public String getCourseCode() {
            return courseCode;
        }

        public Integer getScore() {
            return score;
        }

        public String getLetterGrade() {
            return letterGrade;
        }
    }
}
