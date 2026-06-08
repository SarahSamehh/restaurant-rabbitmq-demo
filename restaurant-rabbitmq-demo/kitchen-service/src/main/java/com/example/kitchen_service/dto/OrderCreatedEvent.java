package com.example.kitchen_service.dto;

public record OrderCreatedEvent(Long id, String customerName, String items) {
}
