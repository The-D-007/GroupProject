package com.database.project.group4.mappers;

import com.database.project.group4.entities.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setStudentID(rs.getInt("student_id"));
        student.setStudentName(rs.getString("name"));
        student.setStudentEmail(rs.getString("email"));
        student.setStudentPhoneNumber(rs.getString("phone_number"));
        student.setStudentDOB(rs.getObject("date_of_birth", LocalDate.class));
        student.setStudentGPA(rs.getDouble("GPA"));
        student.setStudentDepartmentId(rs.getInt("department_id"));
        return student;
    }
}