package com.ogutcenali.mapper;

import com.ogutcenali.dto.request.AuthorAddRequestDto;
import com.ogutcenali.repository.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthorMapper {

    IAuthorMapper INSTANCE = Mappers.getMapper(IAuthorMapper.class);

    Author toAddAuthor(final AuthorAddRequestDto authorAddRequestDto);
}
