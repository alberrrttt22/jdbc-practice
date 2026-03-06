package com.albert.jdbcpractice;

import com.albert.jdbcpractice.domain.Author;
import com.albert.jdbcpractice.domain.Book;

public class TestDataUtil {

    private TestDataUtil() {
    }

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("Author B")
                .age(90)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("Author C")
                .age(100)
                .build();
    }

    public static Book createTestBookA() {
        Book book = Book.builder()
                .isbn("123-456")
                .title("Bla Bla Bla 1")
                .authorId(1L)
                .build();
        return book;
    }

    public static Book createTestBookB() {
        Book book = Book.builder()
                .isbn("234-567")
                .title("Bla Bla Bla 2")
                .authorId(1L)
                .build();
        return book;
    }

    public static Book createTestBookC() {
        Book book = Book.builder()
                .isbn("345-678")
                .title("Bla Bla Bla 3")
                .authorId(1L)
                .build();
        return book;
    }
}
