package com.posadskiy.costaccounting.projects.web.endpoint;

import com.posadskiy.costaccounting.projects.core.controller.ProjectController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("project")
public class ProjectEndpoint {
    
    private final ProjectController projectController;
    
    @Autowired
    public ProjectEndpoint(ProjectController projectController) {
        this.projectController = projectController;
    }

    @GetMapping("all-users-in-project/{projectId}")
    public List<String> getAllByProjectId(@PathVariable final String projectId) {
        return projectController.getAllUsersByProjectId(projectId);
    }
}
