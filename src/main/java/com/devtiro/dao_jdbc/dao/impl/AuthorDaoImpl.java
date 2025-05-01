package com.devtiro.dao_jdbc.dao.impl;

import com.devtiro.dao_jdbc.dao.AuthorDao;
import com.devtiro.dao_jdbc.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

// AuthorDaoImpl is a concrete class that implements the AuthorDao interface
@Repository
public class AuthorDaoImpl implements AuthorDao {
    // It needs a JdbcTemplate (Spring’s helper class for JDBC operations) to
    // send SQL commands to the database

    // JdbcTemplate simplifies normal JDBC (no need for manual Connection,
    // PreparedStatement, etc.)
    private final JdbcTemplate jdbcTemplate;

    // When this class is created, Spring injects an instance of JdbcTemplate into it
    public AuthorDaoImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public void create(Author author) {
        // jdbcTemplate.update(...) executes an SQL INSERT command
        jdbcTemplate.update(
                // The ? are placeholders — they prevent SQL injection and are
                // filled by the provided Java values (author.getId(), etc.)
                "INSERT INTO authors (id, name, age) VALUES (?, ?, ?)",
                author.getId(), author.getName(), author.getAge()
        );
    }

    @Override
    public Optional<Author> findOne(long authorId) {
        List<Author> results =  jdbcTemplate.query(
                "SELECT id, name, age FROM authors WHERE id = ? LIMIT 1",
                new AuthorRowMapper(),
                authorId
        );

        return results.stream().findFirst();
    }

    @Override
    public List<Author> findAll() {
        return jdbcTemplate.query(
                "SELECT id, name, age FROM authors",
                new AuthorRowMapper()
        );

    }

    public static class AuthorRowMapper implements RowMapper<Author>{
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getNString("name"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }
}
