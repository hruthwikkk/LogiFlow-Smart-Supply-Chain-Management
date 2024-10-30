package com.supplychain.repository;

import com.supplychain.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByStatus(Inventory.InventoryStatus status);
    // List<Inventory> findByQuantityLessThanThreshold();
}