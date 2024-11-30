package com.database.project.group4.database;

import com.database.project.group4.entities.Course;
import com.database.project.group4.entities.CourseInstructor;
import com.database.project.group4.entities.Instructor;
import com.database.project.group4.mappers.CourseInstructorRowMapper;
import com.database.project.group4.mappers.CourseRowMapper;
import com.database.project.group4.mappers.InstructorRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InstructorDatabase {
    private NamedParameterJdbcTemplate jdbc;

    public InstructorDatabase(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Instructor> getAll() {
        String query = "SELECT * FROM Instructors";
        List<Instructor> instructors = jdbc.query(query, new InstructorRowMapper());

        for (Instructor instructor : instructors) {
            String courseInstructorQuery = "SELECT * FROM course_instructors WHERE instructor_id = :instructor_id";
            MapSqlParameterSource instructorParams = new MapSqlParameterSource();
            instructorParams.addValue("instructor_id", instructor.getInstructorID());
            List<CourseInstructor> courseInstructors = jdbc.query(courseInstructorQuery, instructorParams, new CourseInstructorRowMapper());

            List<Course> allCourses = new ArrayList<>();
            for (CourseInstructor courseInstructor : courseInstructors) {
                String coursesQuery = "SELECT * FROM courses WHERE course_id = :course_id";
                MapSqlParameterSource courseParams = new MapSqlParameterSource();
                courseParams.addValue("course_id", courseInstructor.getCourse());
                List<Course> coursesList = jdbc.query(coursesQuery, courseParams, new CourseRowMapper());
                allCourses.addAll(coursesList);
            }
            instructor.setInstructorCourses(allCourses);
        }
        return instructors;
    }

}
