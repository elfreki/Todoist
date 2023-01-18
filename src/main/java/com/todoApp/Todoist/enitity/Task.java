package com.todoApp.Todoist.enitity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//TODO add a Priority ENUM(P1 - P4)
@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    //TODO can add notifications based on due_date
    @Column(name = "due_date")
    private LocalDate dueDate;

    //TODO this should be an ENUM
    @Column(name = "status")
    private String status;

    @JsonBackReference(value="taskUserReference")
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @JsonBackReference(value="taskProjectReference")
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToMany
    @JoinTable(name = "tasks_labels",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id"))
    @JsonIgnoreProperties("tasks")
    private List<Label> labels = new ArrayList<>();

    // Getters and setters
}
