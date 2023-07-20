package com.cdpkafka.consumer.controller;

import com.cdpkafka.consumer.service.KafkaConsumerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka-consumer")
public class KafkaConsumerController {

    private final KafkaConsumerService kafkaConsumerService;

    public KafkaConsumerController(KafkaConsumerService kafkaConsumerService) {
        this.kafkaConsumerService = kafkaConsumerService;
    }

    @GetMapping("/consume/{eventId}")
    public String consume(@PathVariable long eventId) {
        return kafkaConsumerService.getWikiEventDataById(eventId);
    }

}
