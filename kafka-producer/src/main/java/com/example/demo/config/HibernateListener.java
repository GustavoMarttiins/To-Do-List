package com.example.demo.config;

import com.example.demo.producer.TaskRequestProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HibernateListener {

    private final EntityManagerFactory entityManagerFactory;
    private final ObjectMapper objectMapper;
    private final TaskRequestProducer producer;

    @PostConstruct
    private void init() {
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(new InsertEventListenerClass(producer, objectMapper));
        registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(new UpdateEventListenerClass(producer, objectMapper));
        registry.getEventListenerGroup(EventType.DELETE).appendListener(new DeleteEventListenerClass(producer));
    }
}
