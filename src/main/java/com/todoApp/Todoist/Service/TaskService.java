package com.todoApp.Todoist.Service;

import com.todoApp.Todoist.Repository.TaskRepository;
import com.todoApp.Todoist.enitity.Label;
import com.todoApp.Todoist.enitity.Project;
import com.todoApp.Todoist.enitity.Task;
import com.todoApp.Todoist.enitity.User;
import com.todoApp.Todoist.vo.TaskRequestVo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectService projectService;

    private final LabelService labelService;

    private final UserService userService;

    @Autowired
    public TaskService(TaskRepository taskRepository, ProjectService projectService, LabelService labelService, UserService userService) {
        this.taskRepository = taskRepository;
        this.projectService = projectService;
        this.labelService = labelService;
        this.userService = userService;
    }

    public Task createTask(String userId, Task task)  {

        task.setUser(userService.getUser(userId));
        try {
            Project project = addProject(userId, task.getProject());
            task.setProject(project);
        }
        catch (Exception e){
            throw new RuntimeException("Cannot Add Project ",e);
        }
        try {
            List<Label> labels = addLabels(userId, task.getLabels());
            task.setLabels(labels);
        } catch (Exception e) {
            throw new RuntimeException("Cannot Add Labels ",e);
        }
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks(String userId) {
        return taskRepository.findAllByUserId(userId);
    }

    public Task getTask(String userId, String id) {
        return taskRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new EntityNotFoundException("Task not found for user: " + userId));
    }

    public Task updateTask(String userId, Task task){
        User user = userService.getUser(userId);
        Task oldTask = getTask(userId, task.getId());
        if(user.equals(oldTask.getUser())) {
            task.setUser(oldTask.getUser());
        }
        else {
            throw new RuntimeException("User not valid ");
        }
        try {
            Project project = addProject(userId, task.getProject());
            task.setProject(project);
        }
        catch (Exception e){
            throw e;
        }
        try {
            List<Label> labels = addLabels(userId ,task.getLabels());
            task.setLabels(labels);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return taskRepository.save(task);
    }

    public void deleteTask(String userId, String id) {
        User user = userService.getUser(userId);
        Task task = getTask(userId,id);
        if(user.equals(task.getUser())){
            taskRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Task not found for user :"+ userId);
        }
    }

    private Project addProject(String userId, Project project){
        if (project != null && project.getId() != null) {
            return projectService.getProject( project.getId(), userId);
        }
        else if (project != null && project.getName() != null) {
            if(projectService.getProjectByName(project.getName()) != null) return projectService.getProjectByName(project.getName());
            else return projectService.createProject(userId, project);
        }
        return null;
    }

    private List<Label> addLabels(String userId, List<Label> labels){
        List<Label> resLabels = new ArrayList<>();
        for(Label label: labels){
            if (label != null && label.getId() != null) {
                resLabels.add(labelService.getLabel(label.getId()));
            }
            else if (label != null && label.getTitle() != null) {
                if(labelService.getLabelByNameAndColour(label.getTitle(), label.getColor()) != null){
                    resLabels.add(labelService.getLabelByNameAndColour(label.getTitle(), label.getColor()));
                }
                else{
                    resLabels.add(labelService.createLabel(userId, label));
                }
            }
        }

        return resLabels;
    }

    public List<Task> findAllByProject(String userId, String id) {
        return taskRepository.findAllByProjectAndUserId(projectService.getProject(userId, id), userId);
    }

//    public List<Task> findAllByLabel(String id) {
//        return taskRepository.findAllByLabels(labelService.getLabel(id));
//    }
}
