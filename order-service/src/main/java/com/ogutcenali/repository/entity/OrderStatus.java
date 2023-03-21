package com.ogutcenali.repository.entity;

import com.ogutcenali.repository.enums.DeliveryStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "orderstatus")
public class OrderStatus extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long orderid;
    @Enumerated(EnumType.STRING)
    DeliveryStatus deliveryStatus;
}
