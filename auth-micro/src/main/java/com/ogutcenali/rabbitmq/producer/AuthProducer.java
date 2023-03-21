package com.ogutcenali.rabbitmq.producer;

import com.ogutcenali.rabbitmq.model.ActivateStatus;
import com.ogutcenali.rabbitmq.model.CreateUserProfileFromAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthProducer {

    private final RabbitTemplate rabbitTemplate;

    public void createUserProfile(CreateUserProfileFromAuth createUserProfileFromAuth) {
        rabbitTemplate.convertAndSend("direct-exchange-auth", "key-auth-create", createUserProfileFromAuth);
    }

    public void activateStatus(ActivateStatus activateStatus){
        rabbitTemplate.convertAndSend("direct-exchange-auth","key-auth-status",activateStatus);
    }

}
