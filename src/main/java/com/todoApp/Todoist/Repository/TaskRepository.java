package com.todoApp.Todoist.Repository;

import com.todoApp.Todoist.enitity.Label;
import com.todoApp.Todoist.enitity.Project;
import com.todoApp.Todoist.enitity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, String> {

    Optional<Task> findByIdAndUserId(String id, String UserId);

    List<Task> findAllByUserId(String userId);

    List<Task> findAllByProjectAndUserId(Project project, String userId);

//    List<Task> findAllByLabel(Label label);
}
