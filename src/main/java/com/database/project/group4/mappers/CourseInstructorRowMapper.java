package com.database.project.group4.mappers;

import com.database.project.group4.entities.CourseInstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseInstructorRowMapper implements RowMapper<CourseInstructor> {

    @Override
    public CourseInstructor mapRow(ResultSet rs, int rowNum) throws SQLException {
        CourseInstructor courseInstructor = new CourseInstructor();
        courseInstructor.setCourseInstructorID(rs.getInt("course_instructor_id"));
        courseInstructor.setCourse(rs.getInt("course_id"));
        courseInstructor.setInstructor(rs.getInt("instructor_id"));
        return courseInstructor;
    }
}
