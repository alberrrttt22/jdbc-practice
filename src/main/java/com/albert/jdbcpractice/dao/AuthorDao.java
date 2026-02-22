package com.albert.jdbcpractice.dao;

import com.albert.jdbcpractice.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findOne(long l);

    List<Author> find();
}
