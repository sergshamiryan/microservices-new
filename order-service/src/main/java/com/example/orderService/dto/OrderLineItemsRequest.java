package com.example.orderService.dto;

import java.math.BigDecimal;

public record OrderLineItemsRequest(Long id, String skuCode, BigDecimal price, Integer quantity) {
}
