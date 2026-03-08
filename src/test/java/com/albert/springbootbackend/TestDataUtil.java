package com.albert.springbootbackend;

import com.albert.springbootbackend.domain.Author;
import com.albert.springbootbackend.domain.Book;

public class TestDataUtil {

    private TestDataUtil() {
    }

    public static Author createTestAuthorA() {
        return Author.builder()
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .name("Author B")
                .age(90)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .name("Author C")
                .age(100)
                .build();
    }

    public static Book createTestBookA(final Author author) {
        Book book = Book.builder()
                .isbn("123-456")
                .title("Bla Bla Bla 1")
                .author(author)
                .build();
        return book;
    }

    public static Book createTestBookB(final Author author) {
        Book book = Book.builder()
                .isbn("234-567")
                .title("Bla Bla Bla 2")
                .author(author)
                .build();
        return book;
    }

    public static Book createTestBookC(final Author author) {
        Book book = Book.builder()
                .isbn("345-678")
                .title("Bla Bla Bla 3")
                .author(author)
                .build();
        return book;
    }
}
