package com.example.inventorservicemicroservice.service;

import com.example.inventorservicemicroservice.dto.InventoryResponse;
import com.example.inventorservicemicroservice.model.Inventory;
import com.example.inventorservicemicroservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory -> new InventoryResponse(inventory.getSkuCode(), inventory.getQuantity() > 0))
                .toList();
    }

    public List<Inventory> getAllStocks() {
        return inventoryRepository.findAll();
    }
}
