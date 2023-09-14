package com.example.orderService.dto;

import com.example.orderService.model.OrderLineItem;

import java.util.List;

public record OrderDto(Long id,
                       String orderNumber) {
}
