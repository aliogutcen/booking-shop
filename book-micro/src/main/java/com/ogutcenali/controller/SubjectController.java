package com.ogutcenali.controller;

import com.ogutcenali.dto.request.SubjectAddRequestDto;
import com.ogutcenali.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ogutcenali.constant.RestEndPoints.*;

@RestController
@RequestMapping(SUBJECT)
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> subjectAdd(@RequestBody SubjectAddRequestDto subjectAddRequestDto){
        return ResponseEntity.ok(subjectService.subjectAdd(subjectAddRequestDto));
    }
}
