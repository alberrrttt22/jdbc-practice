package com.albert.springbootbackend.repositories;

import com.albert.springbootbackend.TestDataUtil;
import com.albert.springbootbackend.domain.Author;
import com.albert.springbootbackend.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTest {

    private final BookRepository underTest;

    @Autowired
    public BookRepositoryIntegrationTest(BookRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatBookCanBeCreatedAndFound(){
        Author author = TestDataUtil.createTestAuthorA();
        Book book = TestDataUtil.createTestBookA(author);

        Book savedBook = underTest.save(book);
        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(savedBook);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndFound(){
        Author author = TestDataUtil.createTestAuthorA();

        Book bookA = TestDataUtil.createTestBookA(author);
        Book bookB = TestDataUtil.createTestBookB(author);
        Book bookC = TestDataUtil.createTestBookC(author);

        Book savedBookA = underTest.save(bookA);
        Book savedBookB = underTest.save(bookB);
        Book savedBookC = underTest.save(bookC);

        Iterable<Book> results = underTest.findAll();
        assertThat(results)
                .hasSize(3)
                .containsExactly(savedBookA, savedBookB, savedBookC);

    }

    @Test
    public void testThatBookCanBeUpdated(){
        Author author = TestDataUtil.createTestAuthorA();
        Book bookA = TestDataUtil.createTestBookA(author);

        Book savedBookA = underTest.save(bookA);
        bookA.setTitle("UPDATED");
        savedBookA = underTest.save(bookA);

        Optional<Book> result = underTest.findById(bookA.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(savedBookA);
    }

    @Test
    public void testThatBookCanBeDeleted(){
        Author author = TestDataUtil.createTestAuthorA();
        Book book = TestDataUtil.createTestBookA(author);

        underTest.save(book);
        underTest.deleteById(book.getIsbn());
        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isEmpty();
    }

}
