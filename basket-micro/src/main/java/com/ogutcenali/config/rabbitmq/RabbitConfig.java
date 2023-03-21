package com.ogutcenali.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private String exchangeOrder = "direct-exchange-order";

    private String exchangeOrderSuccess = "exchange-order-success";
    private String quequeOrder = "queque-create-order";

    private String quequeOrderSuccessBasket = "queque-success-order-basket";

    private String quequeOrderSuccessUser = "queque-success-order-user";

    private String keyOrder = "key-order-create";


    @Bean
    FanoutExchange directFanoutExchange() {
        return new FanoutExchange(exchangeOrderSuccess);
    }

    @Bean
    DirectExchange directExchangeOrder() {
        return new DirectExchange(exchangeOrder);
    }

    @Bean
    Queue quequeOrderSuccessBasket() {
        return new Queue(quequeOrderSuccessBasket);
    }

    @Bean
    Queue quequeOrderSuccessUser() {
        return new Queue(quequeOrderSuccessUser);
    }

    @Bean
    Queue createOrder() {
        return new Queue(quequeOrder);
    }


    @Bean
    Binding bindingOrderSuccessBasket(FanoutExchange  directFanoutExchange , Queue quequeOrderSuccessBasket){
        return  BindingBuilder.bind(quequeOrderSuccessBasket).to(directFanoutExchange);
    }
    @Bean
    Binding bindingOrderSuccessUser(FanoutExchange  directFanoutExchange , Queue quequeOrderSuccessUser){
        return  BindingBuilder.bind(quequeOrderSuccessUser).to(directFanoutExchange);
    }

    @Bean
    Binding bindingCreateOrder(DirectExchange directExchange, Queue createOrder) {
        return BindingBuilder.bind(createOrder).to(directExchange).with(keyOrder);
    }
}
