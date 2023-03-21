package com.ogutcenali.controller;

import com.ogutcenali.dto.request.AuthorAddRequestDto;
import com.ogutcenali.dto.response.GetAuthorResponseDto;
import com.ogutcenali.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ogutcenali.constant.RestEndPoints.*;

@RestController
@RequestMapping(BOOK)
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/author/save")
    public ResponseEntity<Boolean> authorAdd(@RequestBody AuthorAddRequestDto authorAddRequestDto) {
        return ResponseEntity.ok(authorService.authorAdd(authorAddRequestDto));
    }

    @GetMapping("find/{authorid}")
    public ResponseEntity<GetAuthorResponseDto> getAuthor(@PathVariable Long authorid) {
        return ResponseEntity.ok(authorService.getAuthor(authorid));
    }

}
