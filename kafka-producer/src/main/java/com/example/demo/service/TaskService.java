package com.example.demo.service;

import com.example.demo.entity.Task;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Task createTask(Task task) throws JsonProcessingException;

    Optional<Task> updateTask(Long id, Task updatedTask) throws JsonProcessingException;

    Optional<Task> taskById(Long id);

    List<Task> allTasks();

    void deleteTask(Long id) throws JsonProcessingException;
}
