package com.example.demo.service;

import com.example.demo.entity.Task;
import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public Task createTask(Task task) {
        return repository.save(task);
    }

    public Optional<Task> updateTask(Long id, Task updatedTask) {
        Task existingTask = getExistingTask(id);
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.map(updatedTask, existingTask);
        return Optional.of(repository.save(existingTask));
    }

    public Optional<Task> taskById(Long id) {
        return repository.findById(id);
    }

    public List<Task> allTasks() {
        return repository.findAll();
    }

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
