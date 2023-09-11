package com.example.orderService.service;

import com.example.orderService.dto.OrderLineItemsRequest;
import com.example.orderService.dto.OrderPlacedEvent;
import com.example.orderService.dto.OrderRequest;
import com.example.orderService.repository.OrderRepository;
import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderServiceTest {


//    private OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
//    private WebClient.Builder webClient = Mockito.mock(WebClient.Builder.class);
//    private KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate = Mockito.mock(KafkaTemplate.class);

//    @Test
//    @DisplayName("If at least one item isn`t in stock the test will fail")
//    void shouldPlaceOrderIfInStack() {
//        OrderService orderService = new OrderService(orderRepository, webClient, kafkaTemplate);
//        OrderLineItemsRequest orderLineItemsRequest = new OrderLineItemsRequest(1l, "iphone_13", new BigDecimal(100), 1);
//        OrderLineItemsRequest orderLineItemsRequest2 = new OrderLineItemsRequest(1l, "iphone_13", new BigDecimal(100), 1);
//
//        List<OrderLineItemsRequest> orderLineItemsRequestList = new ArrayList<>();
//        orderLineItemsRequestList.add(orderLineItemsRequest);
//        orderLineItemsRequestList.add(orderLineItemsRequest2);
//
//
//        OrderRequest orderRequest = new OrderRequest(orderLineItemsRequestList);
//        assertThrows(NotFoundException.class, () -> orderService.placeOrder(orderRequest));
//    }




}