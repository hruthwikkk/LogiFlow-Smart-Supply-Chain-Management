package com.supplychain.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int quantity;
    private int threshold;

    @Enumerated(EnumType.STRING)
    private InventoryStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum InventoryStatus {
        NORMAL, LOW, CRITICAL, IN_STOCK
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