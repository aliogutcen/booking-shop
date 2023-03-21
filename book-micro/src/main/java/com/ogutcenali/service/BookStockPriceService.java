package com.ogutcenali.service;

import com.ogutcenali.repository.IBookStockPriceRepository;
import com.ogutcenali.repository.entity.BookStockPrice;
import com.ogutcenali.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class BookStockPriceService extends ServiceManager<BookStockPrice, Long> {

    private final IBookStockPriceRepository bookStockPriceRepository;

    public BookStockPriceService(IBookStockPriceRepository bookStockPriceRepository) {
        super(bookStockPriceRepository);
        this.bookStockPriceRepository = bookStockPriceRepository;
    }

    public void addBookWithPriceAndStock(Double price, Integer stock, Long bookId) {
        BookStockPrice bookStockPrice = BookStockPrice.builder()
                .price(price)
                .stock(stock)
                .bookid(bookId)
                .build();
        save(bookStockPrice);
    }
}
