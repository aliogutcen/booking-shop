package com.ogutcenali.rabbitmq.model;

import lombok.*;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderAdd implements Serializable {
    Long authid;
    Map<Long,Integer> bookList;
}
