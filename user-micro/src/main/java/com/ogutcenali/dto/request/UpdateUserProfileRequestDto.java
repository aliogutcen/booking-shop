package com.ogutcenali.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateUserProfileRequestDto {

    Long id;

    String age;

    String about;

    String avatar;
}
