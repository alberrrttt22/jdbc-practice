package com.albert.jdbcpractice.dao.impl;

import com.albert.jdbcpractice.JdbcPracticeApplication;
import com.albert.jdbcpractice.dao.AuthorDao;
import org.springframework.jdbc.core.JdbcTemplate;

public class AuthorDaoImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


}
