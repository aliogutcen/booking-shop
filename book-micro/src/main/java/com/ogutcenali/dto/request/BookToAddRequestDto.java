package com.ogutcenali.dto.request;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookToAddRequestDto {

    String name;
    Integer page;
    String isbn;
    String summary;
    String bookImage;
    Integer stock;
    Double price;
    LocalDate publicationDate;
    List<Long> authorId;
    List<Long> subjectId;
}
