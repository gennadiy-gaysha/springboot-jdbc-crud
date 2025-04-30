package com.devtiro.dao_jdbc.dao.impl;

import com.devtiro.dao_jdbc.TestDataUtil;
import com.devtiro.dao_jdbc.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AuthorDaoImplIntegrationTests {
    private AuthorDaoImpl underTest;

    // Spring injects the real AuthorDaoImpl instance, so the test
    // uses the actual DAO (not a mock)
    @Autowired
    public AuthorDaoImplIntegrationTests(AuthorDaoImpl underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthor(); // creates test Author
        underTest.create(author); // saves it to DB
        Optional<Author> result =  underTest.findOne(author.getId()); // retrieves from DB
        assertThat(result).isPresent(); // checks that something was returned
        assertThat(result.get()).isEqualTo(author); // checks it matches the input
    }
}
