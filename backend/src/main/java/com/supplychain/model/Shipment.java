package com.supplychain.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String origin;
    private String destination;
    
    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;
    
    private LocalDateTime eta;
    private int progress;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public enum ShipmentStatus {
        IN_TRANSIT, DELIVERED, DELAYED
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}