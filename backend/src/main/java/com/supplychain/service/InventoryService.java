package com.supplychain.service;

import com.supplychain.model.Inventory;
import com.supplychain.repository.InventoryRepository;
import com.supplychain.service.AlertService;
import com.supplychain.model.Alert;
import com.supplychain.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final AlertService alertService;
    private final KafkaProducerService kafkaProducer;

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory updateInventoryQuantity(Long id, int quantity) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        inventory.setQuantity(quantity);

        if (quantity <= inventory.getThreshold() * 0.25) {
            inventory.setStatus(Inventory.InventoryStatus.CRITICAL);
            alertService.createAlert(Alert.AlertType.ERROR,
                    "Critical inventory level for " + inventory.getName());
        } else if (quantity <= inventory.getThreshold()) {
            inventory.setStatus(Inventory.InventoryStatus.LOW);
            alertService.createAlert(Alert.AlertType.WARNING,
                    "Low inventory level for " + inventory.getName());
        } else {
            inventory.setStatus(Inventory.InventoryStatus.IN_STOCK);
        }

        Inventory updatedInventory = inventoryRepository.save(inventory);
        kafkaProducer.sendInventoryUpdate(updatedInventory);
        return updatedInventory;
    }

    public void processInventoryUpdate(Inventory inventory) {
        inventoryRepository.save(inventory);
    }
}