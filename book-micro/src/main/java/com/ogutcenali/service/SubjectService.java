package com.ogutcenali.service;

import com.ogutcenali.dto.request.SubjectAddRequestDto;
import com.ogutcenali.exception.BookException;
import com.ogutcenali.exception.EErrorType;
import com.ogutcenali.mapper.ISubjectMapper;
import com.ogutcenali.repository.ISubjectRepository;
import com.ogutcenali.repository.entity.Subject;
import com.ogutcenali.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubjectService extends ServiceManager<Subject, Long> {
    private final ISubjectRepository subjectRepository;

    public SubjectService(ISubjectRepository subjectRepository) {
        super(subjectRepository);
        this.subjectRepository = subjectRepository;
    }

    public Boolean subjectAdd(SubjectAddRequestDto subjectAddRequestDto) {
        Optional<Subject> subject = subjectRepository.findOptionalBySubject(subjectAddRequestDto.getSubject());
        if (subject.isPresent()) throw new BookException(EErrorType.SUBJECT_HAS_BEEN);
        save(ISubjectMapper.INSTANCE.toSubjectAdd(subjectAddRequestDto));
        return true;
    }

    public Long subjectGetIdFromSubjectName(String subjectname) {
        Optional<Subject> subject = subjectRepository.findOptionalBySubject(subjectname);
        return subject.get().getId();
    }

    public String findSubjectNameWithId(Long subjectid) {
        Optional<Subject> subject = findById(subjectid);
        return  subject.get().getSubject();
    }
}
