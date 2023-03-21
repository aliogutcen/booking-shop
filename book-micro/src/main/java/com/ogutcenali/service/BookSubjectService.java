package com.ogutcenali.service;

import com.ogutcenali.exception.BookException;
import com.ogutcenali.exception.EErrorType;
import com.ogutcenali.repository.IBookSubjectRepository;
import com.ogutcenali.repository.entity.BookSubject;
import com.ogutcenali.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookSubjectService extends ServiceManager<BookSubject, Long> {
    private final IBookSubjectRepository bookSubjectRepository;
    private final SubjectService subjectService;

    public BookSubjectService(IBookSubjectRepository bookSubjectRepository, SubjectService subjectService) {
        super(bookSubjectRepository);
        this.bookSubjectRepository = bookSubjectRepository;
        this.subjectService = subjectService;
    }

    public Boolean addBookAndSubject(List<Long> subjectId, Long bookid) {
        for (Long id : subjectId) {
            if (bookSubjectRepository.findBySubjectid(id).isEmpty())
                throw new BookException(EErrorType.SUBJECT_NOT_FOUND);
            BookSubject bookSubject = BookSubject.builder()
                    .bookid(bookid)
                    .subjectid(id)
                    .build();
            save(bookSubject);
        }
        return true;
    }

    public List<String> findSubjectFromBookId(Long id) {
        List<BookSubject> bookSubjects = bookSubjectRepository.findByBookid(id);
        List<String> subject = new ArrayList<>();
        for (BookSubject bookSubject : bookSubjects) {
            subject.add(subjectService.findSubjectNameWithId(bookSubject.getSubjectid()));
        }
        return subject;
    }
}
