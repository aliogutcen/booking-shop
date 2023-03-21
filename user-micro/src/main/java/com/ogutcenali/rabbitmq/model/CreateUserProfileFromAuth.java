package com.ogutcenali.rabbitmq.model;

import com.ogutcenali.repository.enums.ERole;
import com.ogutcenali.repository.enums.EStatus;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateUserProfileFromAuth implements Serializable {

    Long authid;

    String username;

    String mail;
    ERole role;
    EStatus estatus;

}
