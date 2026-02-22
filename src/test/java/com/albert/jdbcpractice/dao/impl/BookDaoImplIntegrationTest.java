package com.albert.jdbcpractice.dao.impl;

import com.albert.jdbcpractice.TestDataUtil;
import com.albert.jdbcpractice.dao.AuthorDao;
import com.albert.jdbcpractice.domain.Author;
import com.albert.jdbcpractice.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
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
        Book book = TestDataUtil.createTestBook();
        authorDao.create(author);
        underTest.create(book);
        book.setAuthorId(author.getId());
        Optional<Book> result = underTest.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

}
