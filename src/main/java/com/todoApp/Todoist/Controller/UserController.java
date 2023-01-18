package com.todoApp.Todoist.Controller;

import com.todoApp.Todoist.Service.UserService;
import com.todoApp.Todoist.enitity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final com.todoApp.Todoist.Service.UserService UserService;

    @Autowired
    public UserController(UserService UserService) {
        this.UserService = UserService;
    }

    @PostMapping
    public User createUser(@RequestBody User User) {
        return UserService.createUser(User);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return UserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return UserService.getUser(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User User) {
        User.setId(id);
        return UserService.updateUser(User);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        UserService.deleteUser(id);
    }

}

