package com.ogutcenali.repository;

import com.ogutcenali.repository.entity.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookAuthorRepository extends JpaRepository<BookAuthor,Long> {


    List<BookAuthor> findByBookid(Long bookid);

    List<BookAuthor> findByAuthid(Long authid);

}
