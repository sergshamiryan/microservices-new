package com.example.orderService.dto.mapperInterfaces;

import com.example.orderService.dto.OrderDTO;
import com.example.orderService.model.Order;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrderDTOMapper implements Function<Order, OrderDTO> {


    @Override
    public OrderDTO apply(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getOrderNumber());
    }
}
