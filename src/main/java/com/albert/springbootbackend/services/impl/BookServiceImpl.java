package com.albert.springbootbackend.services.impl;

import com.albert.springbootbackend.domain.AuthorEntity;
import com.albert.springbootbackend.domain.BookEntity;
import com.albert.springbootbackend.repositories.AuthorRepository;
import com.albert.springbootbackend.repositories.BookRepository;
import com.albert.springbootbackend.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public BookEntity createUpdateBook(String isbn, BookEntity bookEntity) {
        bookEntity.setIsbn(isbn);
        if (bookEntity.getAuthor() != null && bookEntity.getAuthor().getId() != null){
            Long authorId = bookEntity.getAuthor().getId();
            AuthorEntity authorEntity = authorRepository.findById(authorId)
                    .orElseThrow(() -> new RuntimeException("Author not found with id: " + authorId));
            bookEntity.setAuthor(authorEntity);
            log.info("Linking book with isbn: {} to author id: {}", isbn, authorId);
        }
        log.info("Updated/Created book with isbn : {}", bookEntity.getIsbn());
        return bookRepository.save(bookEntity);
    }

    @Override
    public List<BookEntity> findAll() {
        Iterable<BookEntity> books = bookRepository.findAll();
        log.debug("Fetching all books without pagination");
        return StreamSupport
                .stream(books
                        .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Page<BookEntity> findAll(Pageable pageable) {
        log.debug("Fetching all books with pagination");
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<BookEntity> findOne(String isbn) {
        log.debug("Fetching book with isbn : {}", isbn);
        return bookRepository.findById(isbn);
    }

    @Override
    public boolean isExists(String isbn) {
        log.debug("Checking if book with isbn : {} exists", isbn);
        return bookRepository.existsById(isbn);
    }

    @Override
    public BookEntity patchBook(String isbn, BookEntity bookEntity) {
        bookEntity.setIsbn(isbn);
        return bookRepository.findById(isbn).map(
                existingBook -> {
                    Optional.ofNullable(bookEntity.getTitle()).ifPresent(
                            title -> {
                                log.info("Updating title of book with isbn : {}", isbn);
                                existingBook.setTitle(title);
                            }
                    );
                    return bookRepository.save(existingBook);
                }
        ).orElseThrow(() -> {
            log.error("Book not found");
            return new RuntimeException("Book not found!");
        });
    }

    @Override
    public void deleteBook(String isbn) {
        log.info("Deleting book with isbn : {}", isbn);
        bookRepository.deleteById(isbn);
    }
}
