package com.albert.springbootbackend.dao;

import com.albert.springbootbackend.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> findOne(String s);

    List<Book> find();

    void update(String isbn, Book book);

    void delete(Book book);
}
