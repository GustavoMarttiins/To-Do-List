package com.example.demo.config;

import com.example.demo.entity.Task;
import com.example.demo.producer.TaskRequestProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class UpdateEventListenerClass implements PostUpdateEventListener {

    private final TaskRequestProducer producer;
    private final ObjectMapper objectMapper;


    public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
        Task task = (Task) postUpdateEvent.getEntity();
        try {
            String taskJson = objectMapper.writeValueAsString(task);
            String message = "Foi realizada uma atualização na entidade task na data: " + new Date() + taskJson;
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