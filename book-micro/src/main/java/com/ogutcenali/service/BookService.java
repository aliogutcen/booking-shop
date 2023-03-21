package com.ogutcenali.service;

import com.ogutcenali.dto.request.BookToAddRequestDto;
import com.ogutcenali.dto.response.GetAllBookResponseDto;
import com.ogutcenali.dto.response.GetBookResponseDto;
import com.ogutcenali.exception.BookException;
import com.ogutcenali.exception.EErrorType;
import com.ogutcenali.mapper.IBookMapper;
import com.ogutcenali.repository.IBookRepository;
import com.ogutcenali.repository.entity.Book;
import com.ogutcenali.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookService extends ServiceManager<Book, Long> {
    private final IBookRepository bookRepository;
    private final BookAuthorService bookAuthorService;
    private final BookSubjectService bookSubjectService;
    private final BookStockPriceService bookStockPriceService;
    private final AuthorService authorService;

    public BookService(IBookRepository bookRepository, BookAuthorService bookAuthorService, BookSubjectService bookSubjectService, BookStockPriceService bookStockPriceService, AuthorService authorService) {
        super(bookRepository);
        this.bookRepository = bookRepository;
        this.bookAuthorService = bookAuthorService;
        this.bookSubjectService = bookSubjectService;
        this.bookStockPriceService = bookStockPriceService;
        this.authorService = authorService;
    }

    public Boolean bookAdd(BookToAddRequestDto bookToAddRequestDto) {
        Optional<Book> existingBookOptional = bookRepository.findOptionalByName(bookToAddRequestDto.getName());
        if (existingBookOptional.isPresent()) throw new BookException(EErrorType.BOOK_HAS_BEEN);
            Book book = save(IBookMapper.INSTANCE.toBookAdd(bookToAddRequestDto));
            bookAuthorService.addBookAndAuthor(bookToAddRequestDto.getAuthorId(), book.getId());
            bookSubjectService.addBookAndSubject(bookToAddRequestDto.getSubjectId(), book.getId());
            bookStockPriceService.addBookWithPriceAndStock(bookToAddRequestDto.getPrice(), bookToAddRequestDto.getStock(), book.getId());
            return true;
    }
    public List<GetAllBookResponseDto> findAllBooks() {
        return findAll().parallelStream()
                .flatMap(book -> {
                    List<String> author = bookAuthorService.findByAuthorFromBookId(book.getId());
                    List<String> subject = bookSubjectService.findSubjectFromBookId(book.getId());
                    return Stream.of(GetAllBookResponseDto.builder()
                            .authorList(author)
                            .subjectList(subject)
                            .bookno(book.getPage()).name(book.getName()).ISBN(book.getIsbn())
                            .build());
                })
                .collect(Collectors.toList());
    }

    public GetBookResponseDto getBookFromId(Long bookid) {
        Optional<Book> book = findById(bookid);
        if (book.isEmpty()) throw new BookException(EErrorType.BOOK_NOT_FOUND);
        List<String> author = bookAuthorService.findByAuthorFromBookId(book.get().getId());
        List<String> subject = bookSubjectService.findSubjectFromBookId(book.get().getId());
        return GetBookResponseDto.builder()
                .authorList(author)
                .subjectList(subject)
                .name(book.get().getName())
                .ISBN(book.get().getIsbn())
                .bookno(book.get().getPage())
                .build();
    }

//    public void addBook(CreateBook createBook) {
//        Optional<Book> bookOptional = bookRepository.findOptionalByName(createBook.getName());
//        if (bookOptional.isPresent()) throw new BookException(EErrorType.BOOK_HAS_BEEN);
//        Book book = save(IBookMapper.INSTANCE.toBookAddfromAdmin(createBook));
//        bookAuthorService.addBookAndAuthor(createBook.getAuthorname(), book.getId());
//        bookSubjectService.addBookAndSubject(createBook.getSubjectname(), book.getId());
//    }
}
