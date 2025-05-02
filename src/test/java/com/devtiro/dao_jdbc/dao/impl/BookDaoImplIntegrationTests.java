package com.devtiro.dao_jdbc.dao.impl;

import com.devtiro.dao_jdbc.TestDataUtil;
import com.devtiro.dao_jdbc.dao.AuthorDao;
import com.devtiro.dao_jdbc.domain.Author;
import com.devtiro.dao_jdbc.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTests {
    BookDaoImpl underTest;
    AuthorDao authorDao;

    @Autowired
    public BookDaoImplIntegrationTests(BookDaoImpl underTest, AuthorDao authorDao) {
        this.underTest = underTest;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled(){
        Author authorA = TestDataUtil.createTestAuthorA();
        authorDao.create(authorA);

        Book bookA = TestDataUtil.createTestBookA();
        bookA.setAuthorId(authorA.getId()); // Foreign key reference
        underTest.create(bookA);
        Optional<Book> result = underTest.findOne(bookA.getIsbn());
        assertThat(result).isPresent(); // checks that something was returned
        assertThat(result.get()).isEqualTo(bookA); // checks it matches the input
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
        Author authorA = TestDataUtil.createTestAuthorA();
        authorDao.create(authorA);
        Book bookA = TestDataUtil.createTestBookA();
        bookA.setAuthorId(authorA.getId());
        underTest.create(bookA);

        Author authorB = TestDataUtil.createTestAuthorB();
        authorDao.create(authorB);
        Book bookB = TestDataUtil.createTestBookB();
        bookB.setAuthorId(authorB.getId());
        underTest.create(bookB);

        Author authorC = TestDataUtil.createTestAuthorC();
        authorDao.create(authorC);
        Book bookC = TestDataUtil.createTestBookC();
        bookC.setAuthorId(authorC.getId());
        underTest.create(bookC);

        List<Book> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(bookA, bookB, bookC);
    }

    @Test
    public void testThatBookCanBeUpdated(){
        Author authorA = TestDataUtil.createTestAuthorA();
        authorDao.create(authorA);

        Book bookA =  TestDataUtil.createTestBookA();
        bookA.setAuthorId(authorA.getId());
        underTest.create(bookA);

        bookA.setTitle("Updated");
        underTest.update(bookA.getIsbn(), bookA);

        Optional<Book> result = underTest.findOne(bookA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookA);

    }

    @Test
    public void testThatBookCanBeDeleted(){
        Author authorA = TestDataUtil.createTestAuthorA();
        authorDao.create(authorA);

        Book bookA =  TestDataUtil.createTestBookA();
        bookA.setAuthorId(authorA.getId());
        underTest.create(bookA);

        // Verify that book exists before deletion (optional)
        Optional<Book> beforeDelete = underTest.findOne(bookA.getIsbn());
        assertThat(beforeDelete).isPresent();
        assertThat(beforeDelete.get()).isEqualTo(bookA);

        underTest.delete(bookA.getIsbn());

        Optional<Book> result = underTest.findOne(bookA.getIsbn());
        assertThat(result).isEmpty();
    }
}
