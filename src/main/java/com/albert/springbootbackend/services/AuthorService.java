package com.albert.springbootbackend.services;

import com.albert.springbootbackend.domain.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    public AuthorEntity save(AuthorEntity authorEntity);

    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findOne(Long id);

    boolean isExists(Long id);

    AuthorEntity patchAuthor(Long id, AuthorEntity authorEntity);

    void deleteAuthor(Long id);
}
