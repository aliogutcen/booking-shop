package com.ogutcenali.controller;

import com.ogutcenali.dto.request.BookToAddRequestDto;
import com.ogutcenali.dto.response.GetAllBookResponseDto;
import com.ogutcenali.dto.response.GetBookResponseDto;
import com.ogutcenali.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ogutcenali.constant.RestEndPoints.*;

@RestController
@RequestMapping(BOOK)
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    @PostMapping(SAVE)
    public ResponseEntity<Boolean> bookAdd(@RequestBody BookToAddRequestDto bookToAddRequestDto){
        return ResponseEntity.ok(bookService.bookAdd(bookToAddRequestDto));
    }
    @GetMapping(FINDALL)
    public ResponseEntity<List<GetAllBookResponseDto>> getAllBook(){
        return ResponseEntity.ok(bookService.findAllBooks());
    }
    @GetMapping("/{bookid}")
    public ResponseEntity<GetBookResponseDto> getBook(@PathVariable Long bookid){
        return  ResponseEntity.ok(bookService.getBookFromId(bookid));
    }



}
