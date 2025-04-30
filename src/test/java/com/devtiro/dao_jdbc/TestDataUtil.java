package com.devtiro.dao_jdbc;

import com.devtiro.dao_jdbc.domain.Author;
import com.devtiro.dao_jdbc.domain.Book;

public final class TestDataUtil {
    public TestDataUtil() {
    }


    public static Author createTestAuthor() {
        return Author.builder()
                // these are builder methods, not normal setters
                .id(1L)
                .name("Abigail Rose")
                .age(80)
                // creates the actual Author object with those values
                .build();
    }

    public static Book createTestBook() {
        return Book.builder()
                .isbn("1234-56-7890")
                .title("The Shadow in the Attic")
                .authorId(1L)
                .build();
    }
}
