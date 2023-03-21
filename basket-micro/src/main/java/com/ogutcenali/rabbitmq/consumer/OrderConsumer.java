package com.ogutcenali.rabbitmq.consumer;

import com.ogutcenali.rabbitmq.model.OrderSuccess;
import com.ogutcenali.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConsumer {

    private final BasketService basketService;

    @RabbitListener(queues = "queque-success-order-basket")
    public void successOrder(OrderSuccess orderSuccess){
        basketService.successOrderRemoveBasket(orderSuccess);
    }
}
