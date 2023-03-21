package com.ogutcenali.service;

import com.ogutcenali.dto.request.AuthorAddRequestDto;
import com.ogutcenali.dto.response.GetAuthorResponseDto;
import com.ogutcenali.dto.response.GetBookResponseDto;
import com.ogutcenali.exception.BookException;
import com.ogutcenali.exception.EErrorType;
import com.ogutcenali.mapper.IAuthorMapper;
import com.ogutcenali.repository.IAuthorRepository;
import com.ogutcenali.repository.entity.Author;
import com.ogutcenali.repository.entity.BookAuthor;
import com.ogutcenali.utility.ServiceManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService extends ServiceManager<Author, Long> {

    private final IAuthorRepository authorRepository;
    private final BookAuthorService bookAuthorService;

    public AuthorService(IAuthorRepository authorRepository, @Lazy BookAuthorService bookAuthorService) {
        super(authorRepository);
        this.authorRepository = authorRepository;
        this.bookAuthorService = bookAuthorService;
    }

    public Boolean authorAdd(AuthorAddRequestDto authorAddRequestDto) {
        Optional<Author> author = authorRepository.findOptionalByAuthorname(authorAddRequestDto.getAuthorname());
        if (author.isPresent()) throw new BookException(EErrorType.AUTHOR_HAS_BEEN);
        save(IAuthorMapper.INSTANCE.toAddAuthor(authorAddRequestDto));
        return true;
    }

    public Long findAuthorNameForId(String authorname) {
        Optional<Author> author = authorRepository.findOptionalByAuthorname(authorname);
        return author.get().getId();
    }

    public List<String> findAuthorNameForBook(List<BookAuthor> bookAuthors) {
        List<String> authorList = new ArrayList<>();
        for (BookAuthor bookAuthor : bookAuthors) {
            Optional<Author> author = findById(bookAuthor.getAuthid());
            authorList.add(author.get().getAuthorname());
        }
        return authorList;
    }

    public String findAuthorNameWithId(Long authorid) {
        Optional<Author> author = findById(authorid);
        return author.get().getAuthorname();
    }

    public GetAuthorResponseDto getAuthor(Long authorid) {
        Optional<Author> author = findById(authorid);
        if (author.isEmpty()) throw new BookException(EErrorType.AUTHOR_NOT_FOUND);
        List<GetBookResponseDto> bookList = bookAuthorService.getBookAuthorById(authorid);
        return GetAuthorResponseDto.builder()
                .bookList(bookList)
                .build();
    }

    public boolean findById(List<Long> authorId) {


        for (Long aLong : authorId) {
            Optional<Author> author = findById(aLong);
            if (author.isEmpty()) return false;
        }

        return true;
    }

    public boolean findByAuthorWithName(String authorName) {
        Author author = authorRepository.findByAuthorname(authorName);
        return author != null;
    }
}

