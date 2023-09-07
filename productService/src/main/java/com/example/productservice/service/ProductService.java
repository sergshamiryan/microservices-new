package com.example.productservice.service;

import com.example.productservicemicroservice.dto.ProductRequest;
import com.example.productservicemicroservice.dto.ProductResponse;
import com.example.productservicemicroservice.model.Products;
import com.example.productservicemicroservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {


    private final ProductRepository productRepository;


    public void createProduct(ProductRequest productRequest) {
        Products product = Products.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        productRepository.save(product);
        log.info("Product is saved {} ", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Products> products = productRepository.findAll();
        return products.stream()
                .map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Products product){
        return new ProductResponse(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice());
    }
}
