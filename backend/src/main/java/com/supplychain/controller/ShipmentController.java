package com.supplychain.controller;

import com.supplychain.model.Shipment;
import com.supplychain.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ShipmentController {
    private final ShipmentService shipmentService;
    
    @GetMapping
    public ResponseEntity<?> getAllShipments() {
        return ResponseEntity.ok(shipmentService.getAllShipments());
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable Long id,
            @RequestBody Shipment.ShipmentStatus status) {
        return ResponseEntity.ok(shipmentService.updateShipmentStatus(id, status));
    }
    
    @PutMapping("/{id}/progress")
    public ResponseEntity<?> updateProgress(
            @PathVariable Long id,
            @RequestBody Integer progress) {
        return ResponseEntity.ok(shipmentService.updateShipmentProgress(id, progress));
    }
}