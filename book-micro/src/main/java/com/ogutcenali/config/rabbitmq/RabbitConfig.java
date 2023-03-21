package com.ogutcenali.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private String exchange = "direct-exchange-auth";
    private String exchangeAdminBook = "direct-exchange-admin-book";
    private String quequeCreate = "queque-create-auth";
    private String quequeAdminCreateBook = "queque-create-admin-book";

    private String keyAuth = "key-auth-create";
    private String keyAdminBook = "key-admin-create-book";


    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(exchange);
    }
    @Bean
    DirectExchange directExchangeAdmin() {
        return new DirectExchange(exchangeAdminBook);
    }

    @Bean
    Queue createAuth() {
        return new Queue(quequeCreate);
    }
    @Bean
    Queue createAdminBook() {
        return new Queue(quequeAdminCreateBook);
    }

    @Bean
    Binding bindingCreateAuth(DirectExchange directExchange, Queue createAuth) {
        return BindingBuilder.bind(createAuth).to(directExchange).with(keyAuth);
    }
    @Bean
    Binding bindingCreateBookAdmin(DirectExchange directExchangeAdmin, Queue createAdminBook) {
        return BindingBuilder.bind(createAdminBook).to(directExchangeAdmin).with(keyAdminBook);
    }
}
