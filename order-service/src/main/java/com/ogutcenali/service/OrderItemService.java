package com.ogutcenali.service;

import com.ogutcenali.repository.IOrderItemRepository;
import com.ogutcenali.repository.entity.OrderItem;
import com.ogutcenali.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService extends ServiceManager<OrderItem,Long> {

    public OrderItemService(IOrderItemRepository orderItemRepository) {
        super(orderItemRepository);
    }
}
