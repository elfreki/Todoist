package com.todoApp.Todoist.enitity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @JsonManagedReference(value="taskUserReference")
    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<>();

    @JsonManagedReference(value="userProjectReference")
    @OneToMany(mappedBy = "user")
    private List<Project> projects = new ArrayList<>();

    @JsonManagedReference(value="userLabelReference")
    @OneToMany(mappedBy = "user")
    private List<Label> labels = new ArrayList<>();

}