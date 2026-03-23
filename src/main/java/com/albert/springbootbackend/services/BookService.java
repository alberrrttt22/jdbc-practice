package com.albert.springbootbackend.services;

import com.albert.springbootbackend.domain.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {

    public BookEntity createUpdateBook(String isbn, BookEntity bookEntity);

    List<BookEntity> findAll();

    Optional<BookEntity> findOne(String isbn);

    boolean isExists(String isbn);

    BookEntity patchBook(String isbn, BookEntity bookEntity);

    void deleteBook(String isbn);
}
