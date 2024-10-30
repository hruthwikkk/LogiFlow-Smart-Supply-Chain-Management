package com.supplychain.repository;

import com.supplychain.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDateTime;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByType(Alert.AlertType type);
    List<Alert> findByTimestampAfter(LocalDateTime timestamp);
}