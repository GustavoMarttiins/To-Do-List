package com.example.demo.service.impl;

import com.example.demo.entity.Task;
import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public Task createTask(Task task) {
        return repository.save(task);
    }

    @Override
    public Optional<Task> updateTask(Long id, Task updatedTask) {
        Task existingTask = getExistingTask(id);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(updatedTask, existingTask);
        return Optional.of(repository.save(existingTask));
    }

    @Override
    public Optional<Task> taskById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Task> allTasks() {
        return repository.findAll();
    }

    @Override
    public void deleteTask(Long id) {
        Task existingTask = getExistingTask(id);
        repository.deleteById(id);
    }

    private Task getExistingTask(Long id) {
        Optional<Task> existingTask = this.taskById(id);
        if (existingTask.isEmpty()) {
            throw new TaskNotFoundException("Unable to find task: " + id);
        }
        return existingTask.get();
    }
}

