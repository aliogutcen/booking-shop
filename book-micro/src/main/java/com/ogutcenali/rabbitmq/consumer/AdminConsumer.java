package com.ogutcenali.rabbitmq.consumer;

import com.ogutcenali.rabbitmq.model.CreateBook;
import com.ogutcenali.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminConsumer {
    private final BookService bookService;
    @RabbitListener(queues = "queque-create-admin-book")
    public void addBook(CreateBook createBook) {
        try {
         //   bookService.addBook(createBook);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
