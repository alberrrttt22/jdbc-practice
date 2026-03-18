package com.albert.springbootbackend.repositories;


import com.albert.springbootbackend.domain.Author;
import org.hibernate.annotations.processing.HQL;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    Optional<Author> ageLessThan(int i);

    @Query("SELECT a from Author a WHERE a.age > ?1")
    Iterable<Author> findAgeIsMoreThan(int i);
}
