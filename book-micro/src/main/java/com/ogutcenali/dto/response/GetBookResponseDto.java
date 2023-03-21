package com.ogutcenali.dto.response;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetBookResponseDto {
    String name;
    Integer bookno;
    String ISBN;

    List<String> authorList;

    List<String> subjectList;
}
