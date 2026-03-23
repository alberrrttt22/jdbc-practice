package com.albert.springbootbackend.services;

import com.albert.springbootbackend.domain.AuthorEntity;
import com.albert.springbootbackend.repositories.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    public AuthorEntity save(AuthorEntity authorEntity);

    List<AuthorEntity> findAll();

    Page<AuthorEntity> findAll(Pageable pageable);

    Optional<AuthorEntity> findOne(Long id);

    boolean isExists(Long id);

    AuthorEntity patchAuthor(Long id, AuthorEntity authorEntity);

    void deleteAuthor(Long id);
}
