package com.devtiro.dao_jdbc.dao;

import com.devtiro.dao_jdbc.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void create(Author author);
    // This method may or may not return an Author — and it's wrapped in an Optional
    Optional<Author> findOne(long l);

    List<Author> findAll();

    void update(Long id, Author authorA);

    void delete(Long id);
}
