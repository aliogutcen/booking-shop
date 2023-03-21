package com.ogutcenali.mapper;

import com.ogutcenali.rabbitmq.model.CreateUserProfileFromAuth;
import com.ogutcenali.rabbitmq.model.DeleteUserProfileSettingStatus;
import com.ogutcenali.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserProfileMapper {

    IUserProfileMapper INSTANCE = Mappers.getMapper(IUserProfileMapper.class);

    UserProfile toUserProfile(final CreateUserProfileFromAuth createUserProfileFromAuth);

    DeleteUserProfileSettingStatus toUserUpdateProfile(final UserProfile userProfile);
}
