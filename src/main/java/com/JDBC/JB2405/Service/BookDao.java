package com.JDBC.JB2405.Service;

import com.JDBC.JB2405.Entity.Book;
import com.JDBC.JB2405.RowMapper.BookRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public void addBook(Book book)
    {
        String query="insert into book (id, name, description,price,available) values(?, ?, ?,?,?)";
        jdbcTemplate.update(query, book.getId(), book.getName(), book.getDescription(),book.getPrice(),book.isAvailable());
        
    }
    public void updateBook(Book book)
    {
        String query ="update book set name = ?, description = ? where id = ?";
        jdbcTemplate.update(query, book.getName(), book.getDescription(), book.getId());
    }
    public void deleteBook(int id)
    {
        String query = "delete from book where id = ?";
        jdbcTemplate.update(query, id);
    }

    public Book findBookById(int id)
    {
        String query = "select * from book where id = ?";
      return  jdbcTemplate.queryForObject(query, new Object[]{id},  new BookRowMapper());
        
    }
    public List<Book> searchBook(String keyword)
    {
        String query = "select * from book where name like ? or description like ?";
       return jdbcTemplate.query(query, new Object[]{"%" + keyword + "%", "%" + keyword + "%"}, new BookRowMapper());
    }
    public List<Book> findAllBook()
    {
        String query = "select * from book";
        return jdbcTemplate.query(query, new BookRowMapper());
    }
}
