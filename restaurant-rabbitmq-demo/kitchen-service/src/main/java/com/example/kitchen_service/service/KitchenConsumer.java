package com.example.kitchen_service.service;

import com.example.kitchen_service.dto.OrderCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KitchenConsumer {

    public static List<OrderCreatedEvent> receivedOrders = new ArrayList<>();

    @RabbitListener(queues="kitchen.order.queue")
    public void consume(OrderCreatedEvent event) {
        System.out.println("Kitchen Order received");
        System.out.println(event);
        receivedOrders.add(event);
    }

    public List<OrderCreatedEvent> getReceivedOrders() {
        return receivedOrders;
    }

}
