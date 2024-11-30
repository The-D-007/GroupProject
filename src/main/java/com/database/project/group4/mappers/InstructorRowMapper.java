package com.database.project.group4.mappers;

import com.database.project.group4.entities.Instructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InstructorRowMapper implements RowMapper<Instructor> {
    @Override
    public Instructor mapRow(ResultSet rs, int rowNum) throws SQLException {
        Instructor instructor = new Instructor();
        instructor.setInstructorID(rs.getInt("instructor_id"));
        instructor.setInstructorName(rs.getString("name"));
        instructor.setInstructorEmail(rs.getString("email"));
        instructor.setInstructorPhoneNumber(rs.getString("phone_number"));
        instructor.setInstructorDepartment(rs.getInt("department_id"));
        return instructor;
    }
}
