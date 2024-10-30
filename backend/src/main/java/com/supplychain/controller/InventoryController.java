package com.supplychain.controller;

import com.supplychain.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    private final InventoryService inventoryService;
    
    @GetMapping
    public ResponseEntity<?> getAllInventory() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }
    
    @PutMapping("/{id}/quantity")
    public ResponseEntity<?> updateQuantity(
            @PathVariable Long id,
            @RequestBody Integer quantity) {
        return ResponseEntity.ok(inventoryService.updateInventoryQuantity(id, quantity));
    }
}