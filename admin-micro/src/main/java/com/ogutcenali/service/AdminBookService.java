package com.ogutcenali.service;

import com.ogutcenali.dto.request.AuthorAddRequestDto;
import com.ogutcenali.dto.request.BookAddRequestDto;
import com.ogutcenali.manager.IAuthorManager;
import com.ogutcenali.mapper.IAdminMapper;
import com.ogutcenali.rabbitmq.producer.BookConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminBookService {
    private final BookConsumer bookConsumer;
    private final IAuthorManager authorManager;

    public Boolean addBook(BookAddRequestDto bookAddRequestDto) {
        bookConsumer.createBookFromAdmin(IAdminMapper.INSTANCE.toCreateBook(bookAddRequestDto));
        return true;
    }

    @Transactional
    public Boolean addAuthor(AuthorAddRequestDto authorAddRequestDto) {
        authorManager.authorAdd(authorAddRequestDto);
        return true;
    }
}
