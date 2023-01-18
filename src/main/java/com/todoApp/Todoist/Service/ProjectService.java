package com.todoApp.Todoist.Service;

import com.todoApp.Todoist.Repository.ProjectRepository;
import com.todoApp.Todoist.enitity.Project;
import com.todoApp.Todoist.enitity.Task;
import com.todoApp.Todoist.enitity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserService userService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, UserService userService) {
        this.projectRepository = projectRepository;
        this.userService = userService;
    }

    public Project createProject(String userId, Project project) {
        if(userService.getUser(userId) != null){
            project.setUser(userService.getUser(userId));
        }
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects(String userId) {
        return projectRepository.findAllByUserId(userId);
    }

    public Project getProject(String id, String userId) {
        return projectRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new EntityNotFoundException("Project not found with id " + id));
    }

    public Project updateProject(String userId, Project project) {
        User user = userService.getUser(userId);
        project.setUser(user);
        return projectRepository.save(project);
    }

    public void deleteProject(String userId, String id) {
        User user = userService.getUser(userId);
        Project project = getProject(id, userId);
        if(user.equals(project.getUser())){
            projectRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Project not found for user :"+ userId);
        }
    }

    public Project getProjectByName(String name) {
        return projectRepository.findByName(name);
    }
}
