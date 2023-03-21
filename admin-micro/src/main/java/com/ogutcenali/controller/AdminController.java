package com.ogutcenali.controller;

import com.ogutcenali.dto.request.AuthorAddRequestDto;
import com.ogutcenali.dto.request.BookAddRequestDto;

import com.ogutcenali.dto.response.GetBookResponseDto;
import com.ogutcenali.service.AdminBookService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.ogutcenali.constant.RestEndPoints.*;

@RestController
@RequestMapping(ADMIN)   //api/v1/auth
@RequiredArgsConstructor
public class AdminController {

    private final AdminBookService adminBookService;

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> doSaveBook(@RequestBody BookAddRequestDto bookAddRequestDto){
        return ResponseEntity.ok(adminBookService.addBook(bookAddRequestDto));
    }

    @PostMapping("/author/save")
    public ResponseEntity<Boolean> doSaveAuthor(@RequestBody AuthorAddRequestDto authorAddRequestDto){
        return ResponseEntity.ok(adminBookService.addAuthor(authorAddRequestDto));
    }



}
