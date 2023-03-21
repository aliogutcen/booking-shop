package com.ogutcenali.dto.response;

import com.ogutcenali.repository.entity.Author;
import com.ogutcenali.repository.entity.Book;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetAllBookResponseDto {

    String name;
    Integer bookno;
    String ISBN;

    List<String> authorList;

    List<String> subjectList;


}
