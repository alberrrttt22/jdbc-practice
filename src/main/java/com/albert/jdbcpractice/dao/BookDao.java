package com.albert.jdbcpractice.dao;

import com.albert.jdbcpractice.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> findOne(String s);

    List<Book> find();
}
