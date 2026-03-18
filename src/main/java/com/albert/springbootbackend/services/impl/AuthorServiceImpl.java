package com.albert.springbootbackend.services.impl;

import com.albert.springbootbackend.domain.AuthorEntity;
import com.albert.springbootbackend.repositories.AuthorRepository;
import com.albert.springbootbackend.services.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }
}
