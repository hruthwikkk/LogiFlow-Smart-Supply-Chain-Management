package com.supplychain.kafka;

import com.supplychain.model.Alert;
import com.supplychain.model.Inventory;
import com.supplychain.model.Shipment;
import com.supplychain.service.AlertService;
import com.supplychain.service.InventoryService;
import com.supplychain.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);

    private final ShipmentService shipmentService;
    private final InventoryService inventoryService;
    private final AlertService alertService;

    @KafkaListener(topics = "shipments", groupId = "supplychain-group")
    public void consumeShipment(Shipment shipment) {
        log.info("Received shipment update: {}", shipment);
        shipmentService.processShipmentUpdate(shipment);
    }

    @KafkaListener(topics = "inventory", groupId = "supplychain-group")
    public void consumeInventory(Inventory inventory) {
        log.info("Received inventory update: {}", inventory);
        inventoryService.processInventoryUpdate(inventory);
    }

    @KafkaListener(topics = "alerts", groupId = "supplychain-group")
    public void consumeAlert(Alert alert) {
        log.info("Received alert: {}", alert);
        alertService.processAlert(alert);
    }
}