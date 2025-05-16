package com.JDBC.JB2405.Service;

import com.JDBC.JB2405.Entity.Book;
import com.JDBC.JB2405.Entity.IssueBook;
import com.JDBC.JB2405.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Service
public class issueBookDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int insertBook(IssueBook book)
    {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query="insert into issue_book (id, book_id, user_id, issued_date,issued_for_day ,penalty, total_amount,submit_date,is_returned) values(?, ?, ?,?,?,?,?,?,?)";
//       return jdbcTemplate.update(query, book.getId(), book.getBookId(), book.getUserId(), book.getIssuedDate(),book.getIssuedForDay(),book.getPenalty(),book.getTotalAmount(),book.getSubmitDate(),book.isReturned());

       jdbcTemplate.update(con->
       {
           PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
              preparedStatement.setInt(1, book.getId());
                preparedStatement.setInt(2, book.getBookId());
                preparedStatement.setInt(3, book.getUserId());
                preparedStatement.setDate(4, java.sql.Date.valueOf(book.getIssuedDate()));
                preparedStatement.setInt(5, book.getIssuedForDay());
                preparedStatement.setDouble(6, book.getPenalty());
                preparedStatement.setDouble(7, book.getTotalAmount());
                preparedStatement.setDate(8, java.sql.Date.valueOf(book.getSubmitDate()));
                preparedStatement.setBoolean(9, book.isReturned());
                return preparedStatement;
       },keyHolder);
       return keyHolder.getKey().intValue();
    }
    public void updateIssueBook(IssueBook issueBook)
    {
        String query= "update issue_book set book_id = ?, user_id = ?, issued_date = ?, issued_for_day = ?, penalty = ?, total_amount = ?, submit_date = ?, is_returned = ? where id = ?";
        jdbcTemplate.update(query, issueBook.getBookId(), issueBook.getUserId(), issueBook.getIssuedDate(), issueBook.getIssuedForDay(), issueBook.getPenalty(), issueBook.getTotalAmount(), issueBook.getSubmitDate(), issueBook.isReturned(), issueBook.getId());
    }
    public List<IssueBook> getAllIssueBook()
    {
        String query ="select * from issue_book";
        return jdbcTemplate.query(query,(rs, rowNum) -> {
            IssueBook issueBook = new IssueBook();
            issueBook.setId(rs.getInt("id"));
            issueBook.setBookId(rs.getInt("book_id"));
            issueBook.setUserId(rs.getInt("user_id"));
            issueBook.setIssuedDate(rs.getDate("issued_date").toLocalDate());
            issueBook.setIssuedForDay(rs.getInt("issued_for_day"));
            issueBook.setPenalty(rs.getDouble("penalty"));
            issueBook.setTotalAmount(rs.getDouble("total_amount"));
            issueBook.setSubmitDate(rs.getDate("submit_date").toLocalDate());
            issueBook.setReturned(rs.getBoolean("is_returned"));
            return issueBook;
        });
    }
    public IssueBook getbookById(int id)
    {
        String query ="select * from issue_book where id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (rs, rowNum) -> {
            IssueBook issueBook = new IssueBook();
            issueBook.setId(rs.getInt("id"));
            issueBook.setBookId(rs.getInt("book_id"));
            issueBook.setUserId(rs.getInt("user_id"));
            issueBook.setIssuedDate(rs.getDate("issued_date").toLocalDate());
            issueBook.setIssuedForDay(rs.getInt("issued_for_day"));
            issueBook.setPenalty(rs.getDouble("penalty"));
            issueBook.setTotalAmount(rs.getDouble("total_amount"));
            issueBook.setSubmitDate(rs.getDate("submit_date").toLocalDate());
            issueBook.setReturned(rs.getBoolean("is_returned"));
            return issueBook;
        });
    }
}
