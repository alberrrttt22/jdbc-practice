package com.albert.springbootbackend.services.impl;

import com.albert.springbootbackend.domain.AuthorEntity;
import com.albert.springbootbackend.repositories.AuthorRepository;
import com.albert.springbootbackend.services.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity save(AuthorEntity authorEntity) {
        log.info("Saving author: {}", authorEntity.getName());
        return authorRepository.save(authorEntity);
    }

    @Override
    public List<AuthorEntity> findAll() {
        log.debug("Fetching all authors without pagination");
        return StreamSupport
                .stream(authorRepository
                        .findAll()
                        .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Page<AuthorEntity> findAll(Pageable pageable) {
        log.debug("Fetching all authors with pagination");
        return authorRepository.findAll(pageable);
    }

    @Override
    public Optional<AuthorEntity> findOne(Long id) {
        log.debug("Fetching author with id: {}", id);
        return authorRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        log.debug("Checking if author with id : {} exists", id);
        return authorRepository.existsById(id);
    }

    @Override
    public AuthorEntity patchAuthor(Long id, AuthorEntity authorEntity) {;
        authorEntity.setId(id);
        return authorRepository.findById(authorEntity.getId()).map(existingAuthor -> {
            Optional.ofNullable(authorEntity.getName()).ifPresent(
                    name -> {
                log.info("Updating name of author with id : {}", id);
                existingAuthor.setName(name);
            });
            Optional.ofNullable(authorEntity.getAge()).ifPresent(
                    age -> {
                        log.info("Updating age of author with id : {}", id);
                        existingAuthor.setAge(age);
                    }
            );
            return authorRepository.save(existingAuthor);
        }).orElseThrow(()-> {
            log.warn("Author does not exist");
            return new RuntimeException("Author does not exist");
        });
    }

    @Override
    public void deleteAuthor(Long id) {
        log.info("Deleting author with id : {}", id);
        authorRepository.deleteById(id);
    }
}
