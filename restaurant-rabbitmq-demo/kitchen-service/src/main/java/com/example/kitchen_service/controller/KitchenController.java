package com.example.kitchen_service.controller;

import com.example.kitchen_service.dto.OrderCreatedEvent;
import com.example.kitchen_service.service.KitchenConsumer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/receivedOrders")
@CrossOrigin(origins = "*")
public class KitchenController {

    private final KitchenConsumer kitchenConsumer;

    public KitchenController(KitchenConsumer KitchenConsumer, KitchenConsumer kitchenConsumer) {
        this.kitchenConsumer = KitchenConsumer;
    }


    @GetMapping
    public List<OrderCreatedEvent> getOrders() {
        return kitchenConsumer.getReceivedOrders();
    }
}