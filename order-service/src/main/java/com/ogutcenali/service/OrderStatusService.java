package com.ogutcenali.service;

import com.ogutcenali.repository.IOrderStatusRepository;
import com.ogutcenali.repository.entity.Order;
import com.ogutcenali.repository.entity.OrderStatus;
import com.ogutcenali.repository.enums.DeliveryStatus;
import com.ogutcenali.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusService extends ServiceManager<OrderStatus, Long> {

    public OrderStatusService(IOrderStatusRepository orderStatusRepository) {
        super(orderStatusRepository);
    }

    public void createDeliveryStatus(Order order) {
        OrderStatus orderStatus = OrderStatus.builder()
                .orderid(order.getId())
                .deliveryStatus(DeliveryStatus.PENDING)
                .build();
        save(orderStatus);

    }



}
