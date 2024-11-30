package com.database.project.group4.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Departments")
public class Instructor {
    @Id
    @Column(name = "instructor_id")
    private int instructorID;

    @Column(name = "name")
    private String instructorName;

    @Column(name = "email")
    private String instructorEmail;

    @Column(name = "phone_number")
    private String instructorPhoneNumber;

    @Column(name = "department_id")
    private int instructorDepartment;

    private List<Course> instructorCourses;
}
