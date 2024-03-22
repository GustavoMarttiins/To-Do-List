package com.todolist.kafkaconsumer.consumer;

import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class TaskRequestConsumer {



    @KafkaListener(
            topics = "${topicos.task.topic}",
            groupId = "group_id"
    )
    public void consumer(String message) {
        System.out.println("---Message received---" + message);
    }


}
