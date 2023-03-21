package com.ogutcenali.repository;

import com.ogutcenali.repository.entity.BookStockPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookStockPriceRepository extends JpaRepository<BookStockPrice,Long> {
}
