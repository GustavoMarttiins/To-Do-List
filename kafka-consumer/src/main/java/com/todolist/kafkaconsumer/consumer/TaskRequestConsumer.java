package com.todolist.kafkaconsumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


@Service
public class TaskRequestConsumer {

    @KafkaListener(
            topics = "${topicos.task.topic}",
            groupId = "1"
    )
    public void consumer(String message) {
        System.out.println("---Message received---" + message);
        writeToFile(message);
    }

    public void writeToFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Desafio\\dados_modificados.txt", true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
