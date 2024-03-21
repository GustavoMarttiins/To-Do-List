package com.example.demo.service;

import com.example.demo.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Task createTask(Task task);

    Optional<Task> updateTask(Long id, Task updatedTask);

    Optional<Task> taskById(Long id);

    List<Task> allTasks();

    void deleteTask(Long id);
}
