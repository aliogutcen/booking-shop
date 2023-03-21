package com.ogutcenali.repository;

import com.ogutcenali.repository.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findOptionalByAuthorname(String authorname);

    Author findByAuthorname(String authorname);


}
