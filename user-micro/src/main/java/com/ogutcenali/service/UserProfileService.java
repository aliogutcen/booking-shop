package com.ogutcenali.service;

import com.ogutcenali.dto.request.UpdateUserProfileRequestDto;
import com.ogutcenali.exception.EErrorType;
import com.ogutcenali.exception.UserProfileException;
import com.ogutcenali.mapper.IUserProfileMapper;
import com.ogutcenali.rabbitmq.model.ActivateStatus;
import com.ogutcenali.rabbitmq.model.CreateUserProfileFromAuth;
import com.ogutcenali.rabbitmq.producer.AuthProducer;
import com.ogutcenali.repository.IUserProfileRepository;
import com.ogutcenali.repository.entity.UserProfile;
import com.ogutcenali.repository.enums.EStatus;
import com.ogutcenali.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile, Long> {

    private final IUserProfileRepository userProfileRepository;
    private final UserRolesService userRolesService;
    private final AuthProducer authProducer;

    public UserProfileService(IUserProfileRepository userProfileRepository, UserRolesService userRolesService, AuthProducer authProducer) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;
        this.userRolesService = userRolesService;
        this.authProducer = authProducer;
    }

    public void createUserProfile(CreateUserProfileFromAuth createUserProfileFromAuth) {
        UserProfile userProfile = save(IUserProfileMapper.INSTANCE.toUserProfile(createUserProfileFromAuth));
        userRolesService.addRole(userProfile.getId(), createUserProfileFromAuth.getRole());
    }

    public Boolean updateUserProfile(UpdateUserProfileRequestDto updateUserProfileRequestDto) {
        Optional<UserProfile> userProfile = findById(updateUserProfileRequestDto.getId());
        if (userProfile.isEmpty()) throw new UserProfileException(EErrorType.USER_NOT_FOUND);
        userProfile.get().setAge(updateUserProfileRequestDto.getAge());
        userProfile.get().setAbout(updateUserProfileRequestDto.getAbout());
        userProfile.get().setAvatar(updateUserProfileRequestDto.getAvatar());
        update(userProfile.get());
        return true;
    }

    public Optional<UserProfile> findByAuthId(Long authid) {
        return userProfileRepository.findOptionalByAuthid(authid);
    }

    public Boolean deleteUserProfile(Long id) {
        Optional<UserProfile> userProfile = findById(id);
        if (userProfile.isEmpty()) throw new UserProfileException(EErrorType.USER_NOT_FOUND);
        UserProfile user = userProfile.get();
        user.setEstatus(EStatus.DELETE);
        update(user);
        authProducer.updateAuthStatusAfterDeleteByUserId(IUserProfileMapper.INSTANCE.toUserUpdateProfile(user));
        return true;
    }

    public void updateStatusAfterActivate(ActivateStatus activateStatus) {
        Optional<UserProfile> userProfile = findByAuthId(activateStatus.getAuthid());
        if (userProfile.isEmpty()) throw new UserProfileException(EErrorType.USER_NOT_FOUND);
        UserProfile user = userProfile.get();
        user.setEstatus(activateStatus.getEstatus());
        update(user);
    }
}
