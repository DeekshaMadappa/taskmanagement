package com.example.taskmanagement.controller;

import com.example.taskmanagement.Entity.Project;
import com.example.taskmanagement.dto.ProjectResponse;
import com.example.taskmanagement.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public Page<ProjectResponse> getAllProjects(@PageableDefault(size = 10) Pageable pageable) {
        return projectService.getAllProjects(pageable);
    }

    @GetMapping("/{id}")
    public ProjectResponse getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @GetMapping("/owner/{ownerId}")
    public Page<ProjectResponse> getProjectsByOwner(@PathVariable Long ownerId,
                                                    @PageableDefault(size = 10) Pageable pageable) {
        return projectService.getProjectsByOwner(ownerId, pageable);
    }

    @PostMapping
    public ProjectResponse createProject(@Valid @RequestBody Project project) {
        return projectService.createProject(project);
    }

    @PutMapping("/{id}")
    public ProjectResponse updateProject(@PathVariable Long id, @Valid @RequestBody Project project) {
        return projectService.updateProject(id, project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}