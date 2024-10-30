package com.supplychain.service;

import com.supplychain.model.Alert;
import com.supplychain.repository.AlertRepository;
import com.supplychain.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertService {
    private final AlertRepository alertRepository;
    private final KafkaProducerService kafkaProducer;
    
    public List<Alert> getRecentAlerts() {
        LocalDateTime recent = LocalDateTime.now().minusHours(24);
        return alertRepository.findByTimestampAfter(recent);
    }
    
    public Alert createAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert();
        alert.setType(type);
        alert.setMessage(message);
        alert.setTimestamp(LocalDateTime.now());
        Alert savedAlert = alertRepository.save(alert);
        kafkaProducer.sendAlert(savedAlert);
        return savedAlert;
    }
    
    public void processAlert(Alert alert) {
        alertRepository.save(alert);
    }
}