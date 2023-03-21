package com.ogutcenali.mapper;

import com.ogutcenali.dto.request.BookAddRequestDto;
import com.ogutcenali.rabbitmq.model.CreateBook;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAdminMapper {

    IAdminMapper INSTANCE = Mappers.getMapper(IAdminMapper.class);

    CreateBook toCreateBook(final BookAddRequestDto bookAddRequestDto);
}
