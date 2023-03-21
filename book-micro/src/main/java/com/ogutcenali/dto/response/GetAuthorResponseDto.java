package com.ogutcenali.dto.response;

import com.ogutcenali.repository.entity.Book;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetAuthorResponseDto {

    List<GetBookResponseDto> bookList;
}
