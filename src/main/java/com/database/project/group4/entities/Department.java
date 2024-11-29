package com.database.project.group4.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Vector;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    private int departmentID;
    private String departmentName;
    private ArrayList<Course> departmentCourses = new ArrayList<>();
    private ArrayList<Instructor> departmentInstructors = new ArrayList<>();
    private ArrayList<Student> departmentStudents = new ArrayList<>();
}
