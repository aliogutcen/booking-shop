package com.ogutcenali.rabbitmq.producer;

import com.ogutcenali.rabbitmq.model.OrderSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final RabbitTemplate rabbitTemplate;

    public void successOrder(OrderSuccess orderSuccess){
        rabbitTemplate.convertAndSend("exchange-order-success","",orderSuccess);
    }
}
