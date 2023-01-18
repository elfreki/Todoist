package com.todoApp.Todoist.Service;

import com.todoApp.Todoist.Repository.UserRepository;
import com.todoApp.Todoist.enitity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final com.todoApp.Todoist.Repository.UserRepository UserRepository;

    @Autowired
    public UserService(UserRepository UserRepository) {
        this.UserRepository = UserRepository;
    }

    public User createUser(User User) {
        return UserRepository.save(User);
    }

    public List<User> getAllUsers() {
        return UserRepository.findAll();
    }

    public User getUser(String id) {
        return UserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
    }

    public User updateUser(User User) {
        return UserRepository.save(User);
    }

    public void deleteUser(String id) {
        UserRepository.deleteById(id);
    }

}

