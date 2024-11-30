package com.database.project.group4.mappers;

import com.database.project.group4.entities.Registration;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationRowMapper implements RowMapper<Registration> {

    @Override
    public Registration mapRow(ResultSet rs, int rowNum) throws SQLException {
        Registration registration = new Registration();
        registration.setRegistrationID(rs.getInt("registration_id"));
        registration.setRegistrationCourse(rs.getInt("course_id"));
        registration.setRegistrationStudent(rs.getInt("student_id"));
        java.sql.Date sqlDate = rs.getDate("registration_date");
        if (sqlDate != null) {
            registration.setRegistrationDate(sqlDate.toLocalDate());
        }
        return registration;
    }
}
