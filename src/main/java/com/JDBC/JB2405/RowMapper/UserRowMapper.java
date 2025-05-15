package com.JDBC.JB2405.RowMapper;

import com.JDBC.JB2405.Entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper  implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user =new User();
        user.setId(rs.getInt("id"));
        user.setCity(rs.getString("city"));
        user.setEmail(rs.getString("email"));
        user.setName(rs.getString("name"));
        return user;
    }
}
