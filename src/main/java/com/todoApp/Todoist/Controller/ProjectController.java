package com.todoApp.Todoist.Controller;


import com.todoApp.Todoist.Service.ProjectService;
import com.todoApp.Todoist.enitity.Project;
import com.todoApp.Todoist.enitity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService ProjectService;

    @Autowired
    public ProjectController(ProjectService ProjectService) {
        this.ProjectService = ProjectService;
    }

    @PostMapping
    public Project createProject(@RequestHeader("userId") String userId, @RequestBody Project project) {
        return ProjectService.createProject(userId,project);
    }

    @GetMapping
    public List<Project> getAllProjects(@RequestHeader("userId") String userId) {
        return ProjectService.getAllProjects(userId);
    }

    @GetMapping("/{id}")
    public Project getProject(@RequestHeader("userId") String userId, @PathVariable String id) {
        return ProjectService.getProject( id, userId);
    }

    @PutMapping("/{id}")
    public Project updateProject(@RequestHeader("userId") String userId, @PathVariable String id, @RequestBody Project project) {
        project.setId(id);
        return ProjectService.updateProject(userId, project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@RequestHeader("userId") String userId, @PathVariable String id) {
        ProjectService.deleteProject(userId, id);
    }

}
