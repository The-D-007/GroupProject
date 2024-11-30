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
@Table(name = "course_instructor")
public class CourseInstructor {
    @Id
    @Column(name = "course_instructor_id")
    private int courseInstructorID;

    @Column(name = "course_id")
    private int course;

    @Column(name = "instructor_id")
    private int instructor;
}
