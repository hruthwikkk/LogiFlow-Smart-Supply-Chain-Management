package com.supplychain.repository;

import com.supplychain.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByStatus(Inventory.InventoryStatus status);
    List<Inventory> findByQuantityLessThanThreshold();
}