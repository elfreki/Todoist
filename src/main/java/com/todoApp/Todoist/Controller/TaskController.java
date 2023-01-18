package com.todoApp.Todoist.Controller;

import com.todoApp.Todoist.Service.TaskService;
import com.todoApp.Todoist.enitity.Task;
import com.todoApp.Todoist.vo.TaskRequestVo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//TODO Add filtering logic based on labels
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@RequestHeader("userId") String userId , @RequestBody TaskRequestVo taskRequest) {
            Task task = taskRequest.toTask();
            return taskService.createTask(userId, task);
    }

    @GetMapping
    public List<Task> getAllTasks(@RequestHeader("userId") String userId ) {
        return taskService.getAllTasks(userId);
    }

    @GetMapping("/{id}")
    public Task getTask(@RequestHeader("userId") String userId ,@PathVariable String id) {
        return taskService.getTask(userId, id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@RequestHeader("userId") String userId , @PathVariable String id, @RequestBody Task task) {
        task.setId(id);
        try{
            return taskService.updateTask(userId,task);
        }
        catch (Exception e){
            throw new RuntimeException("Error ",e);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@RequestHeader("userId") String userId ,@PathVariable String id) {
        taskService.deleteTask(userId, id);
    }

    @GetMapping("/project/{id}")
    public List<Task> getTasksByProject(@RequestHeader("userId") String userId ,@PathVariable String id) {
        return taskService.findAllByProject(userId, id);
    }

}
