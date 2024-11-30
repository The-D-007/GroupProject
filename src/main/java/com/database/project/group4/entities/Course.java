package com.database.project.group4.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @Column(name = "course_id")
    private int courseID;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "description")
    private String courseDescription;

    @Column(name = "credits")
    private String courseCredits;

    @Column(name = "department_id")
    private Department courseDepartment;

}
