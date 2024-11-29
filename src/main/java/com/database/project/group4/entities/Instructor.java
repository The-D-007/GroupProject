package com.database.project.group4.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Vector;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {
    private int instructorID;
    private String instructorName;
    private String instructorEmail;
    private String instructorPhoneNumber;
    private Department instructorDepartment;
    private ArrayList<CourseInstructor> instructorCourseInstructors = new ArrayList<>();
}
