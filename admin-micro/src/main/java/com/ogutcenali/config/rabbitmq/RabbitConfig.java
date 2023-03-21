package com.ogutcenali.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private String exchangeAdminBook = "direct-exchange-admin-book";
    private String quequeAdminCreateBook = "queque-create-admin-book";

    private String keyAdminBook = "key-admin-create-book";


    @Bean
    DirectExchange directExchangeAdmin() {
        return new DirectExchange(exchangeAdminBook);
    }

    @Bean
    Queue createAdminBook() {
        return new Queue(quequeAdminCreateBook);
    }

    @Bean
    Binding bindingCreateBookAdmin(DirectExchange directExchangeAdmin, Queue createAdminBook) {
        return BindingBuilder.bind(createAdminBook).to(directExchangeAdmin).with(keyAdminBook);
    }
}
