package com.ogutcenali.mapper;

import com.ogutcenali.dto.request.SubjectAddRequestDto;
import com.ogutcenali.repository.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ISubjectMapper {

    ISubjectMapper INSTANCE = Mappers.getMapper(ISubjectMapper.class);

    Subject toSubjectAdd(final SubjectAddRequestDto subjectAddRequestDto);
}
