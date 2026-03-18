package com.albert.springbootbackend.services;

import com.albert.springbootbackend.domain.AuthorEntity;
import org.springframework.stereotype.Service;

public interface AuthorService {

    public AuthorEntity createAuthor(AuthorEntity authorEntity);
}
