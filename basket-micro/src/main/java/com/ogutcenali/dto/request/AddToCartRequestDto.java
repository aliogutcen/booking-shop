package com.ogutcenali.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddToCartRequestDto {


    String token;

    Long bookid;

    Integer quantity;
}
