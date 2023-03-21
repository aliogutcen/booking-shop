package com.ogutcenali.repository;

import com.ogutcenali.repository.entity.BookSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookSubjectRepository extends JpaRepository<BookSubject, Long> {

    List<BookSubject> findByBookid(Long bookid);

    List<BookSubject> findBySubjectid(Long subjectid);
}
