package com.todolist.kafkaconsumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TaskRequestConsumer {

    @KafkaListener(
            topics = "${topicos.task.topic}",
            groupId = "1"
    )
    public void consumer(String message) {
        System.out.println("---Message received---" + message);
    }


}
