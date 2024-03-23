package com.example.demo.config;

import com.example.demo.entity.Task;
import com.example.demo.producer.TaskRequestProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class InsertEventListenerClass implements PostInsertEventListener {


    private final TaskRequestProducer producer;
    private final ObjectMapper objectMapper;

    @Override
    public void onPostInsert(PostInsertEvent postInsertEvent) {
        Task task = (Task) postInsertEvent.getEntity();
        try {
            String taskJson = objectMapper.writeValueAsString(task);
            String message = "Foi realizada uma nova inserção na entidade Task da data: " + new Date() + taskJson;
            producer.sendMessage(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister entityPersister) {
        return false;
    }
}