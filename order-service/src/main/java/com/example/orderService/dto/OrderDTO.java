package com.example.orderService.dto;

import lombok.Builder;

@Builder
public record OrderDTO(Long id,
                       String orderNumber) {
}
