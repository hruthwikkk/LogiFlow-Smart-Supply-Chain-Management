package com.supplychain.service;

import com.supplychain.model.Shipment;
import com.supplychain.repository.ShipmentRepository;
import com.supplychain.model.Alert;
import com.supplychain.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final AlertService alertService;
    private final KafkaProducerService kafkaProducer;
    
    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }
    
    public Shipment updateShipmentStatus(Long id, Shipment.ShipmentStatus status) {
        Shipment shipment = shipmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Shipment not found"));
            
        if (status == Shipment.ShipmentStatus.DELAYED) {
            alertService.createAlert(Alert.AlertType.WARNING, 
                "Shipment #" + id + " has been delayed");
        }
        
        shipment.setStatus(status);
        Shipment updatedShipment = shipmentRepository.save(shipment);
        kafkaProducer.sendShipmentUpdate(updatedShipment);
        return updatedShipment;
    }
    
    public Shipment updateShipmentProgress(Long id, int progress) {
        Shipment shipment = shipmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Shipment not found"));
            
        shipment.setProgress(progress);
        
        if (progress == 100) {
            shipment.setStatus(Shipment.ShipmentStatus.DELIVERED);
            alertService.createAlert(Alert.AlertType.INFO, 
                "Shipment #" + id + " has been delivered");
        }
        
        Shipment updatedShipment = shipmentRepository.save(shipment);
        kafkaProducer.sendShipmentUpdate(updatedShipment);
        return updatedShipment;
    }
    
    public void processShipmentUpdate(Shipment shipment) {
        shipmentRepository.save(shipment);
    }
}