package com.example.taskmanagement.service;

import com.example.taskmanagement.Entity.Project;
import com.example.taskmanagement.dto.ProjectResponse;
import com.example.taskmanagement.exception.ResourceNotFoundException;
import com.example.taskmanagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findAll().stream().map(ProjectResponse::from).toList();
    }

    public ProjectResponse getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(ProjectResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
    }

    public List<ProjectResponse> getProjectsByOwner(Long ownerId) {
        return projectRepository.findByOwnerId(ownerId).stream().map(ProjectResponse::from).toList();
    }

    public ProjectResponse createProject(Project project) {
        return ProjectResponse.from(projectRepository.save(project));
    }

    public ProjectResponse updateProject(Long id, Project updated) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        project.setName(updated.getName());
        project.setDescription(updated.getDescription());
        return ProjectResponse.from(projectRepository.save(project));
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}