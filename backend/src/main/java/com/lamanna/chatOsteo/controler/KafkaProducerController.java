package com.lamanna.chatOsteo.controler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamanna.chatOsteo.service.KafkaProducer;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaProducerController {

    private KafkaProducer kafkaProducer;

    public KafkaProducerController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> publish(){
        kafkaProducer.sendMessage("test");
        return ResponseEntity.ok("Message sent to kafka topic");
    }
}