package com.example.demo.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskRequestProducer {


    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topicos.task.topic}")
    private String topicTaskRequest;

    public String sendMessage(String message) {
        kafkaTemplate.send(topicTaskRequest, message);
        return "Task send to processing";
    }
}
