package com.database.project.group4.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Students")
public class Student {

    @Id
    @Column(name = "student_id")
    private int studentID;

    @Column(name = "name")
    private String studentName;

    @Column(name = "email")
    private String studentEmail;

    @Column(name = "phone_number")
    private String studentPhoneNumber;

    @Column(name = "date_of_birth")
    private LocalDate studentDOB;

    @Column(name = "GPA")
    private Double studentGPA;

    @Column(name = "department_id")
    private int studentDepartmentId;

    private List<Course> courseList;

    private List<Course> availableCourseList;


}

