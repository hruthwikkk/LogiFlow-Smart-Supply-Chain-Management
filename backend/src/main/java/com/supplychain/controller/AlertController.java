package com.supplychain.controller;

import com.supplychain.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AlertController {
    private final AlertService alertService;
    
    @GetMapping("/recent")
    public ResponseEntity<?> getRecentAlerts() {
        return ResponseEntity.ok(alertService.getRecentAlerts());
    }
}