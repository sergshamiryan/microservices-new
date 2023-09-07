package com.example.inventorservicemicroservice;

import com.example.inventorservicemicroservice.model.Inventory;
import com.example.inventorservicemicroservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventorServiceMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventorServiceMicroserviceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(InventoryRepository inventoryRepository) {
        return args -> {
            Inventory inventory = Inventory.builder()
                    .skuCode("iphone_13")
                    .quantity(100)
                    .build();

            Inventory inventory2 = Inventory.builder()
                    .skuCode("iphone_13_red")
                    .quantity(10)
                    .build();
            inventoryRepository.save(inventory);
            inventoryRepository.save(inventory2);
        };

    }

}
