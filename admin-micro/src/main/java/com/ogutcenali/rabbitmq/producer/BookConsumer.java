package com.ogutcenali.rabbitmq.producer;

import com.ogutcenali.rabbitmq.model.CreateBook;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookConsumer {

    private final RabbitTemplate rabbitTemplate;

    public void createBookFromAdmin(CreateBook createBook){
        rabbitTemplate.convertAndSend("direct-exchange-admin-book","key-admin-create-book",createBook);
    }
}
