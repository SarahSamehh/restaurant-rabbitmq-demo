package com.example.order_service.dto;

public record OrderCreatedEvent(Long id, String customerName, String items) {
}
