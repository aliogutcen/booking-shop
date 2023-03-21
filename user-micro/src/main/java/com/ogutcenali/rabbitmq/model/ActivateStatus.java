package com.ogutcenali.rabbitmq.model;

import com.ogutcenali.repository.enums.EStatus;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ActivateStatus implements Serializable {

    Long authid;

    EStatus estatus;
}
