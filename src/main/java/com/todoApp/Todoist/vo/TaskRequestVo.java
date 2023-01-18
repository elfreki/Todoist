package com.todoApp.Todoist.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.todoApp.Todoist.enitity.Label;
import com.todoApp.Todoist.enitity.Project;
import com.todoApp.Todoist.enitity.Task;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestVo {
    private String title;

    private String description;

    private String dueDate;

    private String status;

    private Project project;

    private List<Label> labels = new ArrayList<>();
    public Task toTask(){
        Task task = new Task();
        task.setTitle(this.title);
        task.setDescription(this.description);
        task.setDueDate(LocalDate.parse(this.dueDate));
        task.setStatus(this.status);
        task.setProject(this.project);
        task.setLabels(this.labels);
        return task;
    }
}
