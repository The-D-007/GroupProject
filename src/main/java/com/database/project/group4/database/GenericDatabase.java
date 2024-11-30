package com.database.project.group4.database;

import com.database.project.group4.entities.Course;
import com.database.project.group4.entities.Department;
import com.database.project.group4.entities.User;
import com.database.project.group4.mappers.CourseRowMapper;
import com.database.project.group4.mappers.DepartmentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenericDatabase {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private NamedParameterJdbcTemplate jdbc;

    public GenericDatabase(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public User findUserAccount(String email) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String s = "SELECT * FROM users WHERE email=:email;";
        parameters.addValue("email", email);
        try {
            return jdbc.queryForObject(s, parameters, new BeanPropertyRowMapper<User>(User.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public String getRolesById(Long userid) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT roles.rolename FROM roles,user_role WHERE roles.roleid=user_role.roleid AND userid=:userid;";
        parameters.addValue("userid", userid);
        return jdbc.queryForObject(query, parameters, String.class);

    }

    public List<Department> findAll() {
        String query = "SELECT * FROM Departments";
        List<Department> departments = jdbc.query(query, new DepartmentRowMapper());

        for (Department department : departments) {
            String courseSql = "SELECT * FROM Courses WHERE department_id = :department_id";
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("department_id", department.getDepartmentID());
            List<Course> courses = jdbc.query(courseSql, parameters, new CourseRowMapper());
            department.setCourses(courses);
        }
        return departments;
    }

    public List<Department> getAllDepartment() {
        String query = "SELECT * FROM Departments";
        return jdbc.query(query, new DepartmentRowMapper());
    }

    public void createNewUser(User user) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String s1 = "INSERT INTO users (email, encryptedpassword, enabled) VALUES(:email,:encryptedPassword,true);";
        parameters.addValue("email", user.getEmail());
        parameters.addValue("encryptedPassword", passwordEncoder.encode(user.getEncryptedPassword()));
        jdbc.update(s1, parameters);
    }

    public void addRole(long userId, long roleId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String s2 = "INSERT INTO user_role(userId,roleId) VALUES(:user,:role);";
        parameters.addValue("user", userId);
        parameters.addValue("role", roleId);
        jdbc.update(s2, parameters);
    }
}



