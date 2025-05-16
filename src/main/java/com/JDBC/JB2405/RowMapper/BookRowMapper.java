package com.JDBC.JB2405.RowMapper;

import com.JDBC.JB2405.Entity.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book =new Book();
        book.setId(rs.getInt("id"));
        book.setName(rs.getString("name"));
        book.setDescription(rs.getString("description"));
        book.setPrice(rs.getInt("price"));
        book.setAvailable(rs.getInt("available"));

        return book;
    }
}
