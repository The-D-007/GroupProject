package com.database.project.group4.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Vector;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private int courseID;
    private String courseName;
    private String courseDescription;
    private boolean courseCredits;
    private Department courseDepartment;
    private ArrayList<CourseInstructor> courseInstructor = new ArrayList<>();
    private ArrayList<Registration> courseRegistration = new ArrayList<>();
}
