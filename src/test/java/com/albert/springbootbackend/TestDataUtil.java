package com.albert.springbootbackend;

import com.albert.springbootbackend.domain.AuthorEntity;
import com.albert.springbootbackend.domain.BookEntity;
import com.albert.springbootbackend.domain.dto.AuthorDto;
import com.albert.springbootbackend.domain.dto.BookDto;

public class TestDataUtil {

    private TestDataUtil() {
    }

    public static AuthorEntity createTestAuthorA() {
        return AuthorEntity.builder()
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    public static AuthorDto createTestAuthorDtoA() {
        return AuthorDto.builder()
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    public static AuthorEntity createTestAuthorB() {
        return AuthorEntity.builder()
                .name("AuthorEntity B")
                .age(90)
                .build();
    }

    public static AuthorDto createTestAuthorDtoB() {
        return AuthorDto.builder()
                .name("AuthorDto B")
                .age(90)
                .build();
    }

    public static AuthorEntity createTestAuthorC() {
        return AuthorEntity.builder()
                .name("AuthorEntity C")
                .age(100)
                .build();
    }

    public static AuthorDto createTestAuthorDtoC() {
        return AuthorDto.builder()
                .name("AuthorEntity C")
                .age(100)
                .build();
    }

    public static BookEntity createTestBookA(final AuthorEntity authorEntity) {
        BookEntity bookEntity = BookEntity.builder()
                .isbn("123-456")
                .title("Bla Bla Bla 1")
                .authorEntity(authorEntity)
                .build();
        return bookEntity;
    }

    public static BookDto createTestBookDtoA(final AuthorDto authorDto) {
        BookDto bookDto = BookDto.builder()
                .isbn("123-456")
                .title("Bla Bla Bla 1")
                .authorDto(authorDto)
                .build();
        return bookDto;
    }

    public static BookEntity createTestBookB(final AuthorEntity authorEntity) {
        BookEntity bookEntity = BookEntity.builder()
                .isbn("234-567")
                .title("Bla Bla Bla 2")
                .authorEntity(authorEntity)
                .build();
        return bookEntity;
    }

    public static BookDto createTestBookDtoB(final AuthorDto authorDto) {
        BookDto bookDto = BookDto.builder()
                .isbn("123-456")
                .title("Bla Bla Bla 1")
                .authorDto(authorDto)
                .build();
        return bookDto;
    }

    public static BookEntity createTestBookC(final AuthorEntity authorEntity) {
        BookEntity bookEntity = BookEntity.builder()
                .isbn("345-678")
                .title("Bla Bla Bla 3")
                .authorEntity(authorEntity)
                .build();
        return bookEntity;
    }

    public static BookDto createTestBookDtoC(final AuthorDto authorDto) {
        BookDto bookDto = BookDto.builder()
                .isbn("123-456")
                .title("Bla Bla Bla 1")
                .authorDto(authorDto)
                .build();
        return bookDto;
    }
}
