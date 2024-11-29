package com.database.project.group4.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registration {
    private int registrationID;
    private LocalDate registrationDate;
    private Student registrationStudent;
    private Course registrationCourse;
}
