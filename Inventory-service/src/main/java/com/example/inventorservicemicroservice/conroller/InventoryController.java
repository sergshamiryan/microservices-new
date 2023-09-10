package com.example.inventorservicemicroservice.conroller;

import com.example.inventorservicemicroservice.dto.InventoryResponse;
import com.example.inventorservicemicroservice.model.Inventory;
import com.example.inventorservicemicroservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam("sku-code") List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Inventory> getAllStocks() {
        return inventoryService.getAllStocks();
    }

}
