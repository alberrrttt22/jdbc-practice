package com.albert.springbootbackend.repositories;


import com.albert.springbootbackend.domain.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity,Long> {

    Optional<AuthorEntity> ageLessThan(int i);

    @Query("SELECT a from AuthorEntity a WHERE a.age > ?1")
    Iterable<AuthorEntity> findAgeIsMoreThan(int i);
}
