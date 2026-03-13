package com.example.taskmanagement.dto;

import com.example.taskmanagement.Entity.Project;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectResponse {
    private Long id;
    private String name;
    private String description;
    private UserResponse owner;
    private LocalDateTime createdAt;

    public static ProjectResponse from(Project project) {
        if (project == null) return null;
        ProjectResponse dto = new ProjectResponse();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setOwner(UserResponse.from(project.getOwner()));
        dto.setCreatedAt(project.getCreatedAt());
        return dto;
    }
}