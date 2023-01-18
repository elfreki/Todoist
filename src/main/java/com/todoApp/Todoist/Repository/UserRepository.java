package com.todoApp.Todoist.Repository;

import com.todoApp.Todoist.enitity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
