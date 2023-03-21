package com.ogutcenali.mapper;

import com.ogutcenali.dto.request.CreateAuthRequestDto;
import com.ogutcenali.rabbitmq.model.CreateUserProfileFromAuth;
import com.ogutcenali.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {

    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);

    Auth toCreateAuth(final CreateAuthRequestDto createAuthRequestDto);

    @Mapping(source = "id", target = "authid")
    CreateUserProfileFromAuth createUserProfileFromAuth(final Auth auth);
}
