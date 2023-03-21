package com.ogutcenali.service;

import com.ogutcenali.dto.response.GetBookResponseDto;
import com.ogutcenali.exception.BookException;
import com.ogutcenali.exception.EErrorType;
import com.ogutcenali.repository.IBookAuthorRepository;
import com.ogutcenali.repository.entity.BookAuthor;
import com.ogutcenali.utility.ServiceManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookAuthorService extends ServiceManager<BookAuthor, Long> {

    private final IBookAuthorRepository bookAuthorRepository;
    private final AuthorService authorService;
    private final BookService bookService;

    public BookAuthorService(IBookAuthorRepository bookAuthorRepository, AuthorService authorService, @Lazy BookService bookService) {
        super(bookAuthorRepository);
        this.bookAuthorRepository = bookAuthorRepository;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    public Boolean addBookAndAuthor(List<Long> authorId, Long bookid) {
        for (Long id : authorId) {

            if (bookAuthorRepository.findByAuthid(id)) throw new BookException(EErrorType.AUTHOR_NOT_FOUND);
            BookAuthor bookAuthor = BookAuthor.builder()
                    .bookid(bookid)
                    .authid(id).build();
            save(bookAuthor);
        }
        return true;
    }

    public List<String> findByAuthorFromBookId(Long id) {
        List<BookAuthor> bookAuthors = bookAuthorRepository.findByBookid(id);
        List<String> authorList = new ArrayList<>();
        for (BookAuthor bookAuthor : bookAuthors) {
            authorList.add(authorService.findAuthorNameWithId(bookAuthor.getAuthid()));
        }
        return authorList;
    }

    public List<GetBookResponseDto> getBookAuthorById(Long authorid) {

        List<GetBookResponseDto> getBooks = new ArrayList<>();

        List<BookAuthor> getAllBookAuthorId = bookAuthorRepository.findByAuthid(authorid);
        for (BookAuthor bookAuthor : getAllBookAuthorId) {
            getBooks.add(bookService.getBookFromId(bookAuthor.getBookid()));
        }

        return getBooks;
    }
}
