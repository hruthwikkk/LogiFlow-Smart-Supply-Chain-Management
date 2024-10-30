package com.supplychain.repository;

import com.supplychain.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByType(Alert.AlertType type);
    List<Alert> findByTimestampAfter(LocalDateTime timestamp);
}