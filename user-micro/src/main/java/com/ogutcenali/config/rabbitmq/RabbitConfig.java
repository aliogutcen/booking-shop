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
    private String quequeCreate = "queque-create-auth";
    private String quequeStatusUpdateActivate = "queque-status-update-activate";
    private String quequeStatusUpdateAuth = "queque-status-update-auth";
    private String keyAuth = "direct-exchange-auth";
    private String keyStatus = "key-auth-status";

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Queue createAuth() {
        return new Queue(quequeCreate);
    }
    @Bean
    Queue statusUpdateActivate() {
        return new Queue(quequeStatusUpdateActivate);
    }
    @Bean
    Queue statusUpdateAuth() {
        return new Queue(quequeStatusUpdateAuth);
    }

    @Bean
    Binding bindingStatusUpdateAuth(DirectExchange directExchange, Queue statusUpdateAuth) {
        return BindingBuilder.bind(statusUpdateAuth).to(directExchange).with(keyStatus);
    }
    @Bean
    Binding bindingCreateAuth(DirectExchange directExchange, Queue createAuth) {
        return BindingBuilder.bind(createAuth).to(directExchange).with(keyAuth);
    }
    @Bean
    Binding bindingUpdateActivateStatus(DirectExchange directExchange, Queue statusUpdateActivate) {
        return BindingBuilder.bind(statusUpdateActivate).to(directExchange).with(keyStatus);
    }
}
