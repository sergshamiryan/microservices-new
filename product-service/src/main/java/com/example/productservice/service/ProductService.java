package com.example.productservice.service;


import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.model.Products;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;


    public void createProduct(ProductRequest productRequest, MultipartFile image) throws IOException {
        Products product = Products.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .image(image.getBytes())
                .price(productRequest.price())
                .build();

        productRepository.save(product);
    }

    public List<ProductResponse> getAllProducts() {
        List<Products> products = productRepository.findAll();
        return products.stream()
                .map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Products product) {
        return new ProductResponse(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImage());
    }
}
