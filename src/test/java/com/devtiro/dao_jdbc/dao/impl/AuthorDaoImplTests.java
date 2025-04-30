package com.devtiro.dao_jdbc.dao.impl;

import com.devtiro.dao_jdbc.TestDataUtil;
import com.devtiro.dao_jdbc.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

// This test is checking that DAO code behaves properly — meaning
//  it calls JdbcTemplate.update(...) with correct SQL and parameters:
// 1. It correctly calls the JdbcTemplate with the expected SQL and values.
// 2. It uses the values of the Author object properly (instead of accidentally
// hardcoding wrong stuff).

// What this test does not do:
//It doesn't test the result of findOne().
//It doesn't check if AuthorRowMapper works correctly.
//It doesn't touch a real database — it's a pure unit test.

//  Why this is useful
//It ensures the method calls the database with the correct query.
//It helps catch bugs like typos in SQL or wrong parameter order early.
//It keeps the test fast and isolated (no real DB needed).
@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {
    // Tell Mockito to create a mock of JdbcTemplate. This mock won't execute
    // real database code — it will just record interactions for later verification
    @Mock
    private JdbcTemplate jdbcTemplate;

    // Take the AuthorDaoImpl class, and automatically
    // inject any mocks (like jdbcTemplate) into its constructor
    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {
        // Author.builder() creates a builder object (not an Author yet)
        Author author = TestDataUtil.createTestAuthor();

        underTest.create(author);

        verify(jdbcTemplate).update(
                // The test checks (using verify(...)) that this update() method was called
                // with the correct SQL string:
                eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
                // with the correct values (id, name, age)
                eq(1L), eq("Abigail Rose"), eq(80)
        );
    }

    // This test verifies that the findOne() method in AuthorDaoImpl
    // calls jdbcTemplate.query(...) with the correct SQL and parameters
    @Test
    public void testThatFindOneGeneratesTheCorrectSql() {
        underTest.findOne(1L);

        verify(jdbcTemplate).query(
                // This checks that the method query(...) was called exactly once
                // The SQL string passed was exactly -
                // "SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"
                eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
                // The second argument was any instance of AuthorRowMapper
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                // The third argument was the long value 1L
                eq(1L)
        );
    }
}
