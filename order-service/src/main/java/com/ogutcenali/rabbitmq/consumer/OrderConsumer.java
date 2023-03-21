package com.ogutcenali.rabbitmq.consumer;

import com.ogutcenali.rabbitmq.model.OrderAdd;
import com.ogutcenali.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConsumer {

    private final OrderService orderService;

    @RabbitListener(queues = "queque-create-order")
    public void createOrder(OrderAdd orderAdd){
        try {
            orderService.createOrder(orderAdd);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
