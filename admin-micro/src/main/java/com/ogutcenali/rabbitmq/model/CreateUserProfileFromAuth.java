package com.ogutcenali.rabbitmq.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateUserProfileFromAuth implements Serializable {

    Long authid;

    String username;

    String mail;
}
