package com.database.project.group4.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseInstructor {
    private int courseInstructorID;
    private Course course;
    private Instructor instructor;
}
