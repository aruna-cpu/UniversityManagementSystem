package com.aruna.university.student.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 80)
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @NotBlank
    @Size(max = 80)
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @NotBlank
    @Email
    @Size(max = 160)
    @Column(nullable = false, unique = true)
    private String email;
    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String major;
    @Min(1900)
    @Max(2100)
    @Column(name = "enrollment_year", nullable = false)
    private Integer enrollmentYear;

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(Integer enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }
}
