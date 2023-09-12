package com.example.orderService.service;

import com.example.orderService.dto.InventoryResponse;
import com.example.orderService.dto.OrderLineItemsRequest;
import com.example.orderService.dto.OrderPlacedEvent;
import com.example.orderService.dto.OrderRequest;
import com.example.orderService.model.Order;
import com.example.orderService.model.OrderLineItem;
import com.example.orderService.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void placeOrder(OrderRequest orderRequest) throws URISyntaxException {
        List<OrderLineItem> orderLineItems = orderRequest.orderLineItemsList().stream().map(this::mapToDto).toList();

        Order order = Order.builder().orderNumber(UUID.randomUUID().toString()).orderLineItemList(orderLineItems).build();

        List<String> skuCodes = orderLineItems.stream().map(OrderLineItem::getSkuCode).toList();

        URIBuilder uri = new URIBuilder("http://inventory-service/api/inventory");

        for (String skuCode : skuCodes) {
            uri.addParameter("sku-code", skuCode);
        }


        InventoryResponse[] arr = webClient
                .get()
                .uri(uri.build())
                .retrieve().bodyToMono(InventoryResponse[].class).block();


        if (arr.length > 0 && arr.length == skuCodes.size()) {
            orderRepository.save(order);
//            kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
        } else {
            throw new IllegalArgumentException("Not in stock");
        }


    }

    private OrderLineItem mapToDto(OrderLineItemsRequest orderLineItemsRequest) {
        return OrderLineItem.builder().price(orderLineItemsRequest.price()).quantity(orderLineItemsRequest.quantity()).skuCode(orderLineItemsRequest.skuCode()).build();
    }


    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
