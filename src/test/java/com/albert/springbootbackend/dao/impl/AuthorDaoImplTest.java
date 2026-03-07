package com.albert.springbootbackend.dao.impl;

import com.albert.springbootbackend.TestDataUtil;
import com.albert.springbootbackend.domain.Author;
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
public class AuthorDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreatesAuthorGeneratesTheCorrectSql(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.create(author);

        verify(jdbcTemplate).update(eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
                eq(1L), eq("Abigail Rose"), eq(80)
        );
    }

    @Test
    public void testThatReadsOneReturnsCorrectSql(){
        underTest.findOne(1L);

        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
                any(AuthorDaoImpl.AuthorRowMapper.class),
                eq(1L)
        );
    }

    @Test
    public void testThatReadsAllReturnsCorrectSql(){
        underTest.find();

        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors"),
                any(AuthorDaoImpl.AuthorRowMapper.class)
        );
    }

    @Test
    public void testThatUpdatesReturnsCorrectSql(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.update(author.getId(), author);

        verify(jdbcTemplate).update(
                eq("UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?"),
                eq(author.getId()),
                eq(author.getName()),
                eq(author.getAge()),
                eq(author.getId())
        );
    }

    @Test
    public void testThatDeletesReturnsCorrectSql(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.delete(author);

        verify(jdbcTemplate).update("DELETE FROM authors WHERE id = ?",
                author.getId());
    }
}
