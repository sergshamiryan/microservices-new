package com.example.productservice.service;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.model.Products;
import com.example.productservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        this.productService = new ProductService(productRepository);
    }

    @Test
    void createProduct() throws IOException {
        ProductRequest productRequest = new ProductRequest("Test Product", "Description", new BigDecimal(19));
        productService.createProduct(productRequest,null );

        ArgumentCaptor<Products> productArgumentCaptor = ArgumentCaptor.forClass(Products.class);
        Mockito.verify(productRepository).save(productArgumentCaptor.capture());

        Products capturedProduct = productArgumentCaptor.getValue();
        assertEquals("Test Product", capturedProduct.getName());
        assertEquals("Description", capturedProduct.getDescription());
        assertEquals(new BigDecimal(19),capturedProduct.getPrice());
    }
}