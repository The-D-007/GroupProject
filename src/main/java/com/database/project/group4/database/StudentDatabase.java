package com.database.project.group4.database;

import com.database.project.group4.entities.Course;
import com.database.project.group4.entities.Registration;
import com.database.project.group4.entities.Student;
import com.database.project.group4.mappers.CourseRowMapper;
import com.database.project.group4.mappers.RegistrationRowMapper;
import com.database.project.group4.mappers.StudentRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDatabase {


    private final NamedParameterJdbcTemplate jdbc;

    public StudentDatabase(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Student getStudent(String email) {
        MapSqlParameterSource namedParam = new MapSqlParameterSource();
        String query1 = "SELECT * FROM STUDENTS WHERE email = :email";
        namedParam.addValue("email", email);
        return jdbc.queryForObject(query1, namedParam, new StudentRowMapper());
    }

    public Student getStudentAll(String email) {
        Student student = getStudent(email);

        MapSqlParameterSource param = new MapSqlParameterSource();
        String query2 = "SELECT * FROM registrations WHERE student_id = :student_id";
        param.addValue("student_id", student.getStudentID());
        List<Registration> registrations = jdbc.query(query2, param, new RegistrationRowMapper());

        List<Course> courseList = new ArrayList<>();

        List<Integer> registeredCourseIds = new ArrayList<>();
        for (Registration reg : registrations) {
            registeredCourseIds.add(reg.getRegistrationCourse());
        }

        for (Integer courseId : registeredCourseIds) {
            MapSqlParameterSource params = new MapSqlParameterSource();
            String query3 = "SELECT * FROM courses WHERE course_id = :course_id";
            params.addValue("course_id", courseId);
            List<Course> courses = jdbc.query(query3, params, new CourseRowMapper());
            courseList.addAll(courses);
        }
        student.setCourseList(courseList);

        if (!registeredCourseIds.isEmpty()) {
            MapSqlParameterSource params = new MapSqlParameterSource();
            String query4 = "SELECT * FROM courses WHERE department_id = :department_id AND course_id NOT IN (:course_ids)";
            params.addValue("department_id", student.getStudentDepartmentId());
            params.addValue("course_ids", registeredCourseIds);
            List<Course> availableCourses = jdbc.query(query4, params, new CourseRowMapper());
            student.setAvailableCourseList(availableCourses);
        } else {
            MapSqlParameterSource params = new MapSqlParameterSource();
            String query4 = "SELECT * FROM courses WHERE department_id = :department_id";
            params.addValue("department_id", student.getStudentDepartmentId());
            List<Course> availableCourses = jdbc.query(query4, params, new CourseRowMapper());
            student.setAvailableCourseList(availableCourses);
        }

        return student;
    }

    public void addCourses(List<Integer> courseIds, int studentId) {
        for (Integer courseId : courseIds) {
            MapSqlParameterSource namedParam = new MapSqlParameterSource();
            String query = "INSERT INTO registrations(student_id, course_id, registration_date) VALUES(:student_id, :course_id, :registration_date)";
            namedParam.addValue("student_id", studentId);
            namedParam.addValue("course_id", courseId);
            namedParam.addValue("registration_date", LocalDate.now());
            jdbc.update(query, namedParam);
        }

    }

    public void dropCourse(int courseId, int studentId) {
        String query = "DELETE FROM Registrations WHERE student_id = :student_id AND course_id = :course_id";
        MapSqlParameterSource namedParam = new MapSqlParameterSource();
        namedParam.addValue("student_id", studentId);
        namedParam.addValue("course_id", courseId);
        jdbc.update(query, namedParam);
    }

    public void createNewStudent(Student student) {
        MapSqlParameterSource namedParam = new MapSqlParameterSource();
        String query = "INSERT INTO Students (name, email, phone_number, date_of_birth, department_id, GPA) " + "VALUES (:name, :email, :phone_number, :date_of_birth, :department_id, :GPA)";

        namedParam.addValue("name", student.getStudentName());
        namedParam.addValue("email", student.getStudentEmail());
        namedParam.addValue("phone_number", student.getStudentPhoneNumber());
        namedParam.addValue("date_of_birth", student.getStudentDOB());
        namedParam.addValue("department_id", student.getStudentDepartmentId());
        namedParam.addValue("GPA", student.getStudentGPA());

        jdbc.update(query, namedParam);
    }


}
