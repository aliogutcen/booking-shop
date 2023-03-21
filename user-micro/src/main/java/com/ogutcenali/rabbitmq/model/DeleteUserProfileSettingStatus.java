package com.ogutcenali.rabbitmq.model;

import com.ogutcenali.repository.enums.EStatus;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeleteUserProfileSettingStatus implements Serializable {

    Long authid;
    EStatus estatus;
}
