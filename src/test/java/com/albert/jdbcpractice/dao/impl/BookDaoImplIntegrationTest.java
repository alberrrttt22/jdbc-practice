package com.albert.jdbcpractice.dao.impl;

import com.albert.jdbcpractice.TestDataUtil;
import com.albert.jdbcpractice.dao.AuthorDao;
import com.albert.jdbcpractice.domain.Author;
import com.albert.jdbcpractice.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTest {

    private AuthorDao authorDao;
    private BookDaoImpl underTest;

    @Autowired
    public BookDaoImplIntegrationTest(BookDaoImpl underTest, AuthorDao authorDao) {
        this.authorDao = authorDao;
        this.underTest = underTest;
    }

    @Test
    public void testThatBookCanBeCreatedAndFound(){
        Author author = TestDataUtil.createTestAuthorA();
        Book book = TestDataUtil.createTestBookA();
        authorDao.create(author);
        underTest.create(book);
        book.setAuthorId(author.getId());
        Optional<Book> result = underTest.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndFound(){
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);

        Book bookA = TestDataUtil.createTestBookA();
        bookA.setAuthorId(author.getId());

        Book bookB = TestDataUtil.createTestBookB();
        bookB.setAuthorId(author.getId());

        Book bookC = TestDataUtil.createTestBookC();
        bookC.setAuthorId(author.getId());

        underTest.create(bookA);
        underTest.create(bookB);
        underTest.create(bookC);

        List<Book> results = underTest.find();
        assertThat(results)
                .hasSize(3)
                .containsExactly(bookA, bookB, bookC);

    }

    @Test
    public void testThatBookCanBeUpdated(){
        Book bookA = TestDataUtil.createTestBookA();
        Author author = TestDataUtil.createTestAuthorA();
        bookA.setAuthorId(author.getId());

        Book bookB = TestDataUtil.createTestBookB();
        bookB.setAuthorId(author.getId());

        authorDao.create(author);
        underTest.create(bookA);

        underTest.update(bookA.getIsbn(), bookB);

        Optional<Book> result = underTest.findOne(bookB.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookB);
    }

    @Test
    public void testThatBookCanBeDeleted(){
        Book book = TestDataUtil.createTestBookA();
        Author author = TestDataUtil.createTestAuthorA();

        book.setAuthorId(author.getId());

        authorDao.create(author);
        underTest.create(book);

        underTest.delete(book);
        Optional<Book> result = underTest.findOne(book.getIsbn());
        assertThat(result).isEmpty();
    }

}
