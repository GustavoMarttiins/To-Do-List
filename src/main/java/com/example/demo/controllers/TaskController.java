package com.example.demo.controllers;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;
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
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        List<Task> tasks = taskService.allTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK).getBody();
    }

    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> tasks = taskService.taskById(id);
        return new ResponseEntity<>(tasks, HttpStatus.OK).getBody();
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Task>> updateTask(@PathVariable Long id, @Valid @RequestBody Task task) {
        Optional<Task> updatedTask = taskService.updateTask(id, task);
        return new ResponseEntity<Optional<Task>>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
