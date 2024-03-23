package com.example.demo.config;

import com.example.demo.producer.TaskRequestProducer;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.DeleteContext;
import org.hibernate.event.spi.DeleteEvent;
import org.hibernate.event.spi.DeleteEventListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class DeleteEventListenerClass implements DeleteEventListener {

    private final TaskRequestProducer producer;

    @Override
    public void onDelete(DeleteEvent deleteEvent) throws HibernateException {
        String task = deleteEvent.getEntityName();
        producer.sendMessage(String.format("Foi realizada uma exclusão entidade task em %s.", task, new Date()));
    }

    @Override
    public void onDelete(DeleteEvent deleteEvent, DeleteContext deleteContext) throws HibernateException {
        onDelete(deleteEvent);
    }

}