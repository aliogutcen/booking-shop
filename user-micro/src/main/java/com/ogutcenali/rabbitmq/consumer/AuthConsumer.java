package com.ogutcenali.rabbitmq.consumer;

import com.ogutcenali.rabbitmq.model.ActivateStatus;
import com.ogutcenali.rabbitmq.model.CreateUserProfileFromAuth;
import com.ogutcenali.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthConsumer {


    private final UserProfileService userProfileService;

    @RabbitListener(queues = "queque-create-auth")
    public void createUserProfile (CreateUserProfileFromAuth createUserProfileFromAuth){
        userProfileService.createUserProfile(createUserProfileFromAuth);
    }

    @RabbitListener(queues = "queque-status-update-activate")
    public void updateStatusAfterActivate(ActivateStatus activateStatus){
        userProfileService.updateStatusAfterActivate(activateStatus);
    }
}
