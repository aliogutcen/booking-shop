package com.ogutcenali.dto.response;

import com.ogutcenali.repository.entity.OrderItem;
import lombok.*;

import javax.persistence.OneToMany;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetAllOrderResponseDto {

    Long authid;

    List<OrderItem> orderItemList;
}
