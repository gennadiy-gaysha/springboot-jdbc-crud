package com.devtiro.dao_jdbc.dao.impl;

import com.devtiro.dao_jdbc.TestDataUtil;
import com.devtiro.dao_jdbc.domain.Author;
import com.devtiro.dao_jdbc.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTests {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    BookDaoImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSql() {
        Author authorA = TestDataUtil.createTestAuthorA();

        Book book = TestDataUtil.createTestBookA();
        book.setAuthorId(authorA.getId()); // Assign the correct foreign key

        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq("1234-56-78901"), eq("The Shadow in the Attic"), eq(1L)
        );
    }

    @Test
    public void testThatFindBookGeneratesCorrectSql() {
        underTest.findOne("1234-56-78901");
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq("1234-56-78901")
        );
    }

    @Test
    public void testThatFindManyBooksGeneratesCorrectSql(){
        underTest.findAll();
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateBookGeneratesCorrectSql(){
        Author authorA = TestDataUtil.createTestAuthorA();

        Book bookA = TestDataUtil.createTestBookA();
        bookA.setAuthorId(authorA.getId()); // Assign the correct foreign key

        underTest.update("1234-56-7890-XXX", bookA);

        verify(jdbcTemplate).update(
                "UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
                "1234-56-78901", "The Shadow in the Attic", 1L, "1234-56-7890-XXX"
        );
    }

    @Test
    public void testThatDeleteBookGeneratesCorrectSql(){
        underTest.delete("1234-56-78901");

        verify(jdbcTemplate).update(
                "DELETE FROM books WHERE isbn = ?",
                "1234-56-78901"
        );
    }
}
