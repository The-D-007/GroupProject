package com.database.project.group4.database;

import com.database.project.group4.entities.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GenericDatabase {

    private NamedParameterJdbcTemplate jdbc;

    public GenericDatabase(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public User findUserAccount(String email)
    {
        MapSqlParameterSource parameters=new MapSqlParameterSource();
        String s="SELECT * FROM users WHERE email=:email;";
        parameters.addValue("email", email);
        try
        {
            return jdbc.queryForObject(s, parameters, new BeanPropertyRowMapper<User>(User.class));
        }
        catch(EmptyResultDataAccessException e)
        {
            return null;
        }
    }
    public String getRolesById(Long userid)
    {
        MapSqlParameterSource parameters=new MapSqlParameterSource();
        String s="SELECT roles.rolename FROM roles,user_role WHERE roles.roleid=user_role.roleid AND userid=:userid;";
        parameters.addValue("userid", userid);
        return jdbc.queryForObject(s, parameters, String.class);

    }

}
