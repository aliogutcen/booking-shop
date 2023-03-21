package com.ogutcenali.repository.entity;

import com.ogutcenali.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "userprofile")
public class UserProfile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long authid;

    String username;

    String mail;

    String age;

    String about;

    String avatar;

    @Enumerated(EnumType.STRING)
    EStatus estatus;

}
