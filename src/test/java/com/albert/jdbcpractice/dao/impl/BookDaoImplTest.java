package com.albert.jdbcpractice.dao.impl;

import com.albert.jdbcpractice.TestDataUtil;
import com.albert.jdbcpractice.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSql(){
        Book book = TestDataUtil.createTestBook();

        underTest.create(book);

        verify(jdbcTemplate).update(eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq("123-456"), eq("Bla Bla Bla"), eq(1L));
    }

    @Test
    public void testThatReadsOneReturnsCorrectSQL(){
        underTest.findOne("123-456");

        verify(jdbcTemplate).query(eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"), any(BookDaoImpl.BookRowMapper.class), eq("123-456"));
    }

}
