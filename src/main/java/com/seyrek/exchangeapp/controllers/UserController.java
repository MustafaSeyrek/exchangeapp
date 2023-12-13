package com.seyrek.exchangeapp.controllers;

import com.seyrek.exchangeapp.entities.User;
import com.seyrek.exchangeapp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return new ResponseEntity<>(userService.getUserById(id), OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserById(@PathVariable int id, @RequestBody User newUser) {
        User user = userService.updateUserById(id, newUser);
        if (user != null)
            return new ResponseEntity<>(OK);
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(OK);
    }

}

