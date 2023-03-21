package com.ogutcenali.repository.entity;

import com.ogutcenali.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tbl_auth")
public class Auth extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    String username;
    String password;
    @Column(unique = true)
    String mail;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    EStatus estatus = EStatus.ACTIVE;
}
