package com.officialshivam.HomeLab.WorkerManagement.controllers;

import com.officialshivam.HomeLab.WorkerManagement.services.kafka.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaService kafkaService;

    @PostMapping("/{message}")
    public ResponseEntity<?> sendToKafka(@PathVariable String message) {
        try {
            kafkaService.sendMessage(message);
            return ResponseEntity.ok("Message sent to Kafka successfully");
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
