package com.ogutcenali.rabbitmq.producer;

import com.ogutcenali.rabbitmq.model.OrderAdd;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final RabbitTemplate rabbitTemplate;

    public void orderAdd(OrderAdd orderAdd){
        rabbitTemplate.convertAndSend("direct-exchange-order","key-order-create",orderAdd);
    }
}
