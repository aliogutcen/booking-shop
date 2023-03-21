package com.ogutcenali.rabbitmq.consumer;

import com.ogutcenali.rabbitmq.model.DeleteUserProfileSettingStatus;
import com.ogutcenali.repository.entity.Auth;
import com.ogutcenali.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileConsumers {

    private final AuthService authService;

    @RabbitListener(queues = "queque-status-update-auth")
    public void updateStatus(DeleteUserProfileSettingStatus deleteUserProfileSettingStatus) {
        Optional<Auth> auth = authService.findById(deleteUserProfileSettingStatus.getAuthid());
        auth.get().setEstatus(deleteUserProfileSettingStatus.getEstatus());
        authService.update(auth.get());
    }
}
