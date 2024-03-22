package com.example.demo.controllers;

import com.example.demo.dto.TaskDTO;
import com.example.demo.entity.Task;
import com.example.demo.service.impl.TaskServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskServiceImpl;

    @GetMapping
    public List<Task> getAllTasks() {
        List<Task> tasks = taskServiceImpl.allTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK).getBody();
    }

    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> tasks = taskServiceImpl.taskById(id);
        return new ResponseEntity<>(tasks, HttpStatus.OK).getBody();
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) throws JsonProcessingException {
        Task createdTask = taskServiceImpl.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Task>> updateTask(@PathVariable Long id, @Valid @RequestBody Task task) throws JsonProcessingException {
        Optional<Task> updatedTask = taskServiceImpl.updateTask(id, task);
        return new ResponseEntity<Optional<Task>>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) throws JsonProcessingException {
        taskServiceImpl.deleteTask(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
