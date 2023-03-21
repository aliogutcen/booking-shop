package com.ogutcenali.repository;

import com.ogutcenali.repository.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<Book,Long> {

    Optional<Book> findOptionalByName(String bookname);


}
