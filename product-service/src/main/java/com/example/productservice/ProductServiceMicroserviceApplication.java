package com.example.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceMicroserviceApplication.class, args);
    }

}
