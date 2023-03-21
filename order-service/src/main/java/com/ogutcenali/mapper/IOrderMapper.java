package com.ogutcenali.mapper;

import com.ogutcenali.dto.response.GetAllOrderResponseDto;
import com.ogutcenali.repository.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IOrderMapper {

    IOrderMapper INSTANCE = Mappers.getMapper(IOrderMapper.class);


    GetAllOrderResponseDto toResponseDto(final Order order);


}
