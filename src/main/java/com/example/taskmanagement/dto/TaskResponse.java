package com.example.taskmanagement.dto;

import com.example.taskmanagement.Entity.Task;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime dueDate;
    private ProjectResponse project;
    private UserResponse assignedUser;

    public static TaskResponse from(Task task) {
        if (task == null) return null;
        TaskResponse dto = new TaskResponse();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setDueDate(task.getDueDate());
        dto.setProject(ProjectResponse.from(task.getProject()));
        dto.setAssignedUser(UserResponse.from(task.getAssignedUser()));
        return dto;
    }
}