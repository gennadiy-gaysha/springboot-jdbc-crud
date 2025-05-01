package com.devtiro.dao_jdbc;

import com.devtiro.dao_jdbc.domain.Author;
import com.devtiro.dao_jdbc.domain.Book;

public final class TestDataUtil {
    public TestDataUtil() {
    }


    public static Author createTestAuthorA() {
        return Author.builder()
                // these are builder methods, not normal setters
                .id(1L)
                .name("Abigail Rose")
                .age(80)
                // .build() invokes a constructor of Book, passing in the values above
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("Cynthia Ozick")
                .age(96)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("Rachel Hore")
                .age(65)
                .build();
    }

    public static Book createTestBookA() {
        return Book.builder()
                .isbn("1234-56-78901")
                .title("The Shadow in the Attic")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookB(){
        return Book.builder()
                .isbn("1234-56-78902")
                .title("The Puttermesser Papers")
                .authorId(2L)
                .build();
    }

    public static Book createTestBookC(){
        return Book.builder()
                .isbn("1234-56-78903")
                .title("The Dream House")
                .authorId(3L)
                .build();
    }

}
