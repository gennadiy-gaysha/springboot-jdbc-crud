package com.devtiro.dao_jdbc.dao;

import com.devtiro.dao_jdbc.dao.impl.BookDaoImpl;
import com.devtiro.dao_jdbc.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    public void testThatCreateBookGeneratesCorrectSql(){
        Book book = Book.builder()
                .isbn("1234-56-7890")
                .title("The Shadow in the Attic")
                .authorId(1L)
                .build();

        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq("1234-56-7890"), eq("The Shadow in the Attic"), eq(1L)
        );

    }
}
