package com.ogutcenali.dto.request;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookAddRequestDto {

    String name;
    Integer bookno;
    String ISBN;
    List<String> authorname;

    List<String> subjectname;

}
