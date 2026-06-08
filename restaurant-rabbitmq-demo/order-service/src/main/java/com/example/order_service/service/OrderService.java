package com.example.order_service.service;

import com.example.order_service.dto.CreateOrderRequest;
import com.example.order_service.dto.OrderCreatedEvent;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderEventPublisher orderEventPublisher;

    public OrderService(OrderRepository orderRepository , OrderEventPublisher orderEventPublisher) {
        this.orderRepository = orderRepository;
        this.orderEventPublisher = orderEventPublisher;
    }

    public Order createOrder(CreateOrderRequest request){
        Order order = new Order();
        order.setCustomerName(request.customerName());
        order.setItems(request.items());

        Order savedOrder = orderRepository.save(order);
        OrderCreatedEvent event = new OrderCreatedEvent(savedOrder.getId(), savedOrder.getCustomerName(), savedOrder.getItems());
        orderEventPublisher.publishOrderCreatedEvent(event);

        return orderRepository.save(order);
    }
}
