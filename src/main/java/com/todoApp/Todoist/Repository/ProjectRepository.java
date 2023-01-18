package com.todoApp.Todoist.Repository;

import com.todoApp.Todoist.enitity.Project;
import com.todoApp.Todoist.enitity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, String> {
    Project findByName(String name);
    List<Project> findAllByUserId(String userId);

    Optional<Project> findByIdAndUserId( String id, String userId);
}
