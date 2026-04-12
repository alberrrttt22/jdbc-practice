package com.albert.afterword.controllers;

import com.albert.afterword.domain.BookEntity;
import com.albert.afterword.domain.dto.BookDto;
import com.albert.afterword.mappers.Mapper;
import com.albert.afterword.services.impl.BookServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class BookController {

    private final BookServiceImpl bookService;

    private final Mapper<BookEntity, BookDto> bookMapper;

    public BookController(Mapper<BookEntity, BookDto> bookMapper, BookServiceImpl bookService){
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> createUpdateBook(@PathVariable("isbn") String isbn,
                                                    @RequestBody BookDto bookDto){
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        // Check the boolean first before you create/update to return accurate Http status code
        boolean bookExists = bookService.isExists(isbn);
        BookEntity savedBookEntity = bookService.createUpdateBook(isbn, bookEntity);
        if (bookExists){
            return ResponseEntity.status(HttpStatus.OK).body(bookMapper.mapTo(savedBookEntity));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(bookMapper.mapTo(savedBookEntity));
    }

    @GetMapping("/books")
    public Page<BookDto> getAllBooks(Pageable pageable){
        Page<BookEntity> books = bookService.findAll(pageable);
        return books.map(bookMapper::mapTo);
    }

    @GetMapping("/books/{isbn}")
    public ResponseEntity<BookDto> getBook(@PathVariable("isbn") String isbn){
        Optional<BookEntity> foundBook = bookService.findOne(isbn);
        return foundBook.map(bookEntity -> {
            BookDto bookDto = bookMapper.mapTo(bookEntity);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/books/{isbn}")
    public ResponseEntity<BookDto> patchBook(@PathVariable("isbn") String isbn,
                                             @RequestBody BookDto bookDto){
        if (!bookService.isExists(isbn)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity savedBook = bookService.patchBook(isbn, bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(savedBook), HttpStatus.OK);
    }

    @DeleteMapping("/books/{isbn}")
    public ResponseEntity deleteBook(@PathVariable("isbn") String isbn){
        if (!bookService.isExists(isbn)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        bookService.deleteBook(isbn);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
