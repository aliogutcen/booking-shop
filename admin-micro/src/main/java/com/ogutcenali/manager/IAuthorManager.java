package com.ogutcenali.manager;

import com.ogutcenali.dto.request.AuthorAddRequestDto;
import com.ogutcenali.dto.response.GetBookResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "author-feign",
        url = "http://localhost:8082/api/v1/book",
        decode404 = true
)
public interface IAuthorManager {
    @PostMapping("/author/save")
    public ResponseEntity<Boolean> authorAdd(@RequestBody AuthorAddRequestDto authorAddRequestDto);

    @GetMapping("/{bookid}")
    public ResponseEntity<GetBookResponseDto> getBook(@PathVariable Long bookid);
}
