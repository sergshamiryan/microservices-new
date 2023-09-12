package com.example.orderService.service;

import com.example.orderService.dto.InventoryResponse;
import com.example.orderService.dto.OrderLineItemsRequest;
import com.example.orderService.dto.OrderPlacedEvent;
import com.example.orderService.dto.OrderRequest;
import com.example.orderService.model.Order;
import com.example.orderService.model.OrderLineItem;
import com.example.orderService.repository.OrderRepository;

import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;


class OrderServiceTest {


    OrderService orderService;

    private OrderRepository orderRepository = Mockito.mock(OrderRepository.class);

    @Mock
    private KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;


    private WebClient webClientMock = Mockito.mock(WebClient.class);

    private WebClient.RequestHeadersSpec requestHeadersSpecMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
    ;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
    ;
    @Mock
    private WebClient.ResponseSpec responseSpecMock = Mockito.mock(WebClient.ResponseSpec.class);
    ;


    @BeforeEach
    void setUp() {

        orderService = new OrderService(orderRepository, webClientMock, kafkaTemplate);
    }


    @Test
    void shouldGetOrders() {
        List<Order> orders = new ArrayList<>();
        List<OrderLineItem> orderLineItems = new ArrayList<>();
        orderLineItems.add(new OrderLineItem(1l, "iphone", new BigDecimal(1000), 5));
        orderLineItems.add(new OrderLineItem(2l, "iphone_13", new BigDecimal(1000), 5));
        orders.add(new Order(1l, "789", orderLineItems));

        when(orderRepository.findAll()).thenReturn(orders);
        List<Order> orderList = orderService.getOrders();

        Assertions.assertEquals(orderList, orders);
    }

    @Test
    @DisplayName("If at least one item isn`t in stock the test will fail")
    void shouldPlaceOrderIfInStack() throws URISyntaxException {
        OrderLineItemsRequest orderLineItemsRequest = new OrderLineItemsRequest(1l, "iphone_13", new BigDecimal(100), 1);
        OrderLineItemsRequest orderLineItemsRequest2 = new OrderLineItemsRequest(1l, "iphone_13_pro", new BigDecimal(100), 1);

        List<OrderLineItemsRequest> orderLineItemsRequestList = new ArrayList<>();
        orderLineItemsRequestList.add(orderLineItemsRequest);
        orderLineItemsRequestList.add(orderLineItemsRequest2);

        InventoryResponse[] inventoryResponses = new InventoryResponse[2];
        inventoryResponses[0] = new InventoryResponse("iphone_13_as", true);
        inventoryResponses[1] = new InventoryResponse("iphone_13_pros", false);

        when(webClientMock.get())
                .thenReturn(requestHeadersUriSpecMock);


        URIBuilder uri = new URIBuilder("http://inventory-service/api/inventory");
        uri.addParameter("sku-code",orderLineItemsRequest.skuCode());
        uri.addParameter("sku-code",orderLineItemsRequest2.skuCode());

        when(requestHeadersUriSpecMock.uri(uri.build()))
                .thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve())
                .thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(InventoryResponse[].class))
                .thenReturn(Mono.just(inventoryResponses));


        OrderRequest orderRequest = new OrderRequest(orderLineItemsRequestList);

        assertDoesNotThrow(() -> orderService.placeOrder(orderRequest));
    }


}