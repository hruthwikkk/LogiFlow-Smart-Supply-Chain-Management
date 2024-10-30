package com.supplychain.model;

public class Shipment {
    public enum ShipmentStatus {
        PENDING, SHIPPED, DELIVERED, DELAYED
    }

    private Long id;
    private ShipmentStatus status;
    private int progress;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShipmentStatus getStatus() {
        return status;
    }

    public void setStatus(ShipmentStatus status) {
        this.status = status;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}