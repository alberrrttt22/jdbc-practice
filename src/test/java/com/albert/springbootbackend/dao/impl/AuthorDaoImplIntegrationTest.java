package com.albert.springbootbackend.dao.impl;

import com.albert.springbootbackend.TestDataUtil;
import com.albert.springbootbackend.domain.Author;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDaoImplIntegrationTest {

    private AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaoImplIntegrationTest(AuthorDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndFound(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.create(author);
        Optional<Author> result = underTest.findOne(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);

    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndFound(){
        Author authorA = TestDataUtil.createTestAuthorA();
        Author authorB = TestDataUtil.createTestAuthorB();
        Author authorC = TestDataUtil.createTestAuthorC();

        underTest.create(authorA);
        underTest.create(authorB);
        underTest.create(authorC);

        List<Author> result = underTest.find();
        assertThat(result).hasSize(3).containsExactly(authorA, authorB, authorC);
    }

    @Test
    public void testThatAuthorCanBeUpdated(){
        Author authorA = TestDataUtil.createTestAuthorA();
        Author authorB = TestDataUtil.createTestAuthorB();

        underTest.create(authorA);

        underTest.update(authorA.getId(), authorB);
        // Now have authorA entry is replaced by authorB
        Optional<Author> author = underTest.findOne(authorB.getId());
        assertThat(author).isPresent();
        assertThat(author.get()).isEqualTo(authorB);
    }

    @Test
    public void testThatAuthorCanBeDeleted(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.create(author);
        underTest.delete(author);

        Optional<Author> result = underTest.findOne(author.getId());
        assertThat(result).isEmpty();
    }
}
