package com.ogutcenali.rabbitmq.producer;

import com.ogutcenali.rabbitmq.model.DeleteUserProfileSettingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthProducer {

    private final RabbitTemplate rabbitTemplate;

    public void updateAuthStatusAfterDeleteByUserId(DeleteUserProfileSettingStatus deleteUserProfileSettingStatus) {
        rabbitTemplate.convertAndSend("direct-exchange-auth", "key-auth-status", deleteUserProfileSettingStatus);
    }
}
