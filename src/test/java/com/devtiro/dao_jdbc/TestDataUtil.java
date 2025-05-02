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

    // Setting authorId = null avoids hardcoding an ID that may not match the one
    // generated in the test. It gives full control to inject a valid foreign key
    // (from an actual created Author)

    public static Book createTestBookA() {
        return Book.builder()
                .isbn("1234-56-78901")
                .title("The Shadow in the Attic")
                .authorId(null)
                .build();
    }

    public static Book createTestBookB(){
        return Book.builder()
                .isbn("1234-56-78902")
                .title("The Puttermesser Papers")
                .authorId(null)
                .build();
    }

    public static Book createTestBookC(){
        return Book.builder()
                .isbn("1234-56-78903")
                .title("The Dream House")
                .authorId(null)
                .build();
    }

}
