package com.ogutcenali.mapper;

import com.ogutcenali.dto.request.BookToAddRequestDto;
import com.ogutcenali.rabbitmq.model.CreateBook;
import com.ogutcenali.repository.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBookMapper {

    IBookMapper INSTANCE = Mappers.getMapper(IBookMapper.class);

    Book toBookAdd(final BookToAddRequestDto bookToAddRequestDto);

    Book toBookAddfromAdmin(final CreateBook createBook);
}
