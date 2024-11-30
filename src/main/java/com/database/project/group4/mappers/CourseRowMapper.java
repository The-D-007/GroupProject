package com.database.project.group4.mappers;

import com.database.project.group4.entities.Course;
import com.database.project.group4.entities.Department;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRowMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();
        course.setCourseID(rs.getInt("course_id"));
        course.setCourseName(rs.getString("course_name"));
        course.setCourseDescription(rs.getString("description"));
        course.setCourseCredits(rs.getString("credits"));

        Department department = new Department();
        department.setDepartmentID(rs.getInt("department_id"));
        course.setCourseDepartment(department);

        return course;
    }
}
