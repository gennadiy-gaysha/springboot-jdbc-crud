package com.devtiro.dao_jdbc.dao.impl;

import com.devtiro.dao_jdbc.dao.BookDao;
import com.devtiro.dao_jdbc.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDaoImpl implements BookDao {
    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update(
                "INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)",
                book.getIsbn(), book.getTitle(), book.getAuthorId()
        );
    }

    @Override
    public Optional<Book> findOne(String isbn) {
        List<Book> results =  jdbcTemplate.query(
                "SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1",
                new BookDaoImpl.BookRowMapper(),
                isbn
        );

        return results.stream().findFirst();
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query(
            "SELECT isbn, title, author_id FROM books",
                new BookRowMapper()
        );
    }

    @Override
    public void update(String isbn, Book bookA) {
        jdbcTemplate.update(
                "UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
                bookA.getIsbn(), bookA.getTitle(), bookA.getAuthorId(), isbn
        );
    }

    @Override
    public void delete(String isbn) {
        jdbcTemplate.update(
                "DELETE FROM books WHERE isbn = ?",
                isbn
        );
    }

    public static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getNString("title"))
                    .authorId(rs.getLong("author_id"))
                    .build();
        }
    }
}
