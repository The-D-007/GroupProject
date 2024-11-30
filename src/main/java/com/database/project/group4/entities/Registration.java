package com.database.project.group4.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "registrations")
public class Registration {
    @Id
    @Column(name = "registration_id")
    private int registrationID;

    @Column(name = "student_id")
    private int registrationStudent;

    @Column(name = "course_id")
    private int registrationCourse;

    @Column(name = "registration_date")
    private LocalDate registrationDate;
}
