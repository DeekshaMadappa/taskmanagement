package com.example.taskmanagement.controller;

import com.example.taskmanagement.Entity.Task;
import com.example.taskmanagement.dto.TaskResponse;
import com.example.taskmanagement.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public TaskResponse createTask(@Valid @RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/status/{status}")
    public Page<TaskResponse> getTasksByStatus(@PathVariable String status,
                                               @PageableDefault(size = 10) Pageable pageable) {
        return taskService.getTasksByStatus(status, pageable);
    }

    @GetMapping("/project/{projectId}")
    public Page<TaskResponse> getTasksByProject(@PathVariable Long projectId,
                                                @PageableDefault(size = 10) Pageable pageable) {
        return taskService.getTasksByProject(projectId, pageable);
    }

    @GetMapping("/user/{userId}")
    public Page<TaskResponse> getTasksByUser(@PathVariable Long userId,
                                             @PageableDefault(size = 10) Pageable pageable) {
        return taskService.getTasksByUser(userId, pageable);
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id, @Valid @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @PutMapping("/{id}/status")
    public TaskResponse updateTaskStatus(@PathVariable Long id, @RequestParam String status) {
        return taskService.updateTaskStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}