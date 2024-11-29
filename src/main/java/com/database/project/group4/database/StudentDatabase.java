package com.database.project.group4.database;

import com.database.project.group4.entities.Student;
import com.database.project.group4.mappers.StudentRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDatabase {


    private final NamedParameterJdbcTemplate jdbc;

    public StudentDatabase(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

//    public String insertStudent(Student student){
//        MapSqlParameterSource namedParam = new MapSqlParameterSource();
//        String query = "INSERT INTO STUDENTS (name, email, phone_number, date_of_birth, department_id, gpa) VALUES (:name, :email, :phone_number, :date_of_birth, :department_id, :gpa);";
//        namedParam.addValue("name", student.getStudentName());
//        namedParam.addValue("email", student.getStudentEmail());
//        namedParam.addValue("phone_number", student.getStudentPhoneNumber());
//        namedParam.addValue("date_of_birth", student.getStudentDOB());
//        namedParam.addValue("department_id", student.getStudentDepartment());
//        namedParam.addValue("gpa", student.getStudentGPA());
//        int done = jdbc.update(query, namedParam);
//        if (done > 0){
//            return "OK";
//        }
//        return "ERROR";
//    }

    public Student getStudent(String email) {
        MapSqlParameterSource namedParam = new MapSqlParameterSource();
        String query = "SELECT * FROM STUDENTS WHERE email = :email";
        namedParam.addValue("email", email);
        System.out.println("The email is: " + email);
        try {
            return jdbc.queryForObject(query, namedParam, new StudentRowMapper());
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }



}
