package com.example.taskmanagement.service;

import com.example.taskmanagement.Entity.Task;
import com.example.taskmanagement.dto.TaskResponse;
import com.example.taskmanagement.exception.ResourceNotFoundException;
import com.example.taskmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskResponse createTask(Task task) {
        return TaskResponse.from(taskRepository.save(task));
    }

    public TaskResponse getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(TaskResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

    public List<TaskResponse> getTasksByStatus(String status) {
        return taskRepository.findByStatus(status).stream().map(TaskResponse::from).toList();
    }

    public List<TaskResponse> getTasksByProject(Long projectId) {
        return taskRepository.findByProjectId(projectId).stream().map(TaskResponse::from).toList();
    }

    public List<TaskResponse> getTasksByUser(Long userId) {
        return taskRepository.findByAssignedUserId(userId).stream().map(TaskResponse::from).toList();
    }

    public TaskResponse updateTask(Long id, Task updated) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        task.setTitle(updated.getTitle());
        task.setDescription(updated.getDescription());
        task.setStatus(updated.getStatus());
        task.setDueDate(updated.getDueDate());
        task.setProject(updated.getProject());
        task.setAssignedUser(updated.getAssignedUser());
        return TaskResponse.from(taskRepository.save(task));
    }

    public TaskResponse updateTaskStatus(Long id, String status) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        task.setStatus(status);
        return TaskResponse.from(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
