package com.ogutcenali.service;


import com.ogutcenali.dto.response.GetAllOrderResponseDto;
import com.ogutcenali.mapper.IOrderMapper;
import com.ogutcenali.rabbitmq.model.OrderAdd;
import com.ogutcenali.rabbitmq.model.OrderSuccess;
import com.ogutcenali.rabbitmq.producer.OrderProducer;
import com.ogutcenali.repository.IOrderRepository;
import com.ogutcenali.repository.entity.Order;
import com.ogutcenali.repository.entity.OrderItem;
import com.ogutcenali.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService extends ServiceManager<Order, Long> {

    private final IOrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final OrderProducer orderProducer;
    private final OrderStatusService orderStatusService;

    public OrderService(IOrderRepository orderRepository, OrderItemService orderItemService, OrderProducer orderProducer, OrderStatusService orderStatusService) {
        super(orderRepository);
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
        this.orderProducer = orderProducer;
        this.orderStatusService = orderStatusService;
    }

    public void createOrder(OrderAdd newOrder) {
        if (newOrder == null || newOrder.getBookList() == null || newOrder.getAuthid() == null) {
            throw new IllegalArgumentException("Invalid order information");
        }
        List<OrderItem> orders = newOrder.getBookList().entrySet().stream()
                .map(entry -> OrderItem.builder()
                        .bookid(entry.getKey())
                        .quantity(entry.getValue())
                        .build())
                .map(orderItemService::save)
                .collect(Collectors.toList());
        Order order = Order.builder()
                .authid(newOrder.getAuthid())
                .orderItemList(orders)
                .build();
        save(order);
        orderStatusService.createDeliveryStatus(order);
        orderProducer.successOrder(OrderSuccess.builder()
                .authid(newOrder.getAuthid())
                .orderid(order.getId())
                .build());
    }

    public List<GetAllOrderResponseDto> getAllOrderByUserId(Long authid) {
        List<GetAllOrderResponseDto> getAllOrders = new ArrayList<>();
        List<Order> orders = orderRepository.findByAuthid(authid);
        orders.forEach(x -> {
            getAllOrders.add(IOrderMapper.INSTANCE.toResponseDto(x));
        });
        return getAllOrders;

    }
}
