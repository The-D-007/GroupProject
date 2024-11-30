package com.database.project.group4.mappers;

import com.database.project.group4.entities.Department;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentRowMapper implements RowMapper<Department> {
    @Override
    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
        Department department = new Department();
        department.setDepartmentID(rs.getInt("department_id"));
        department.setDepartmentName(rs.getString("department_name"));
        return department;
    }
}