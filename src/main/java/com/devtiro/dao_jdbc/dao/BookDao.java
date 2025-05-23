package com.devtiro.dao_jdbc.dao;

import com.devtiro.dao_jdbc.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> findOne(String isbn);

    List<Book> findAll();

    void update(String isbn, Book bookA);

    void delete(String isbn);
}
