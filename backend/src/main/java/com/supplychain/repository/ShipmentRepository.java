package com.supplychain.repository;

import com.supplychain.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    List<Shipment> findByStatus(Shipment.ShipmentStatus status);
    List<Shipment> findByOriginAndDestination(String origin, String destination);
}