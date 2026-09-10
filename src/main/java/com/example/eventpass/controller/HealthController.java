// controller/HealthController.java
package com.example.eventpass.controller;

import com.example.eventpass.dto.HealthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    private final String application;

    public HealthController(
            @Value("${spring.application.name}") String application
    ) {
        this.application = application;
    }

    @GetMapping("/api/health")
    public ResponseEntity<HealthResponse> health() {
        return ResponseEntity.ok(
                new HealthResponse(application, "UP")
        );
    }
}