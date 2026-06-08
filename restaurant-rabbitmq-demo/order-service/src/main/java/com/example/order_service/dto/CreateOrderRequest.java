package com.example.order_service.dto;

public record CreateOrderRequest(
        String customerName,
        String items
) {}