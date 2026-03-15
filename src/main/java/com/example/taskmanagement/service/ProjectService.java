package com.example.taskmanagement.service;

import com.example.taskmanagement.Entity.Project;
import com.example.taskmanagement.dto.ProjectResponse;
import com.example.taskmanagement.exception.ResourceNotFoundException;
import com.example.taskmanagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Page<ProjectResponse> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable).map(ProjectResponse::from);
    }

    public ProjectResponse getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(ProjectResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
    }

    public Page<ProjectResponse> getProjectsByOwner(Long ownerId, Pageable pageable) {
        return projectRepository.findByOwnerId(ownerId, pageable).map(ProjectResponse::from);
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