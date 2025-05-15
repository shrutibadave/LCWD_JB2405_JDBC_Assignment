package com.JDBC.JB2405.Service;

import com.JDBC.JB2405.Entity.User;
import com.JDBC.JB2405.RowMapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveUser(User user)
    {
        String query = "INSERT INTO user (id,name,city, email) VALUES (?, ?,?,?)";
        jdbcTemplate.update(query, user.getId(), user.getName(), user.getCity(), user.getEmail());
    }
    public User getUser(int id)
    {
    String query ="SELECT * FROM user where id =?";
    return jdbcTemplate.queryForObject(query, new Object[]{id}, new UserRowMapper());
    }
    public List<User> getallUser()
    {
        String query = "SELECT * FROM user";
       return jdbcTemplate.query(query, (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("name"), rs.getString("city"), rs.getString("email")));
    }

    public void updateUser(User user)
    {
        String query = "UPDATE user SET name=?, city=?, email=? WHERE id=?";
        jdbcTemplate.update(query, user.getName(), user.getCity(), user.getEmail(), user.getId());
    }
    public void searchUser(String keyword)
    {
        String query = "SELECT * FROM user WHERE name LIKE ? OR city LIKE ?";
        jdbcTemplate.query(query, new Object[]{"%" + keyword + "%", "%" + keyword + "%"}, new UserRowMapper());
    }

    public void deleteUser(int id)
    {
        String query = "DELETE FROM user WHERE id=?";
        jdbcTemplate.update(query, id);
    }
}
