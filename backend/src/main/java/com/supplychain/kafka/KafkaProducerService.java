package com.supplychain.kafka;

import com.supplychain.model.Alert;
import com.supplychain.model.Inventory;
import com.supplychain.model.Shipment;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendShipmentUpdate(Shipment shipment) {
        kafkaTemplate.send("shipments", String.valueOf(shipment.getId()), shipment);
    }

    public void sendInventoryUpdate(Inventory inventory) {
        kafkaTemplate.send("inventory", String.valueOf(inventory.getId()), inventory);
    }

    public void sendAlert(Alert alert) {
        kafkaTemplate.send("alerts", String.valueOf(alert.getId()), alert);
    }
}