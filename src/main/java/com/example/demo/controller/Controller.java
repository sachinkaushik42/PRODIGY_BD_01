package com.example.demo.controller;

import com.example.demo.model.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/users")
public class Controller {

    // In-memory storage acting as our temporary database
    private final Map<UUID, User> userStorage = new ConcurrentHashMap<>();

    // 1. Create (Post)
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        user.setId(UUID.randomUUID()); // Automatically generate unique id
        userStorage.put(user.getId(), user);
        return new ResponseEntity<>(user, HttpStatus.CREATED); 
    }

    // 2. Read All (Get)
    @GetMapping
    public ResponseEntity<Collection<User>> getAllUsers() {
        return ResponseEntity.ok(userStorage.values()); 
    }

    // 3. Read One by One Id 
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        if (!userStorage.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Returns 404
        }
        return ResponseEntity.ok(userStorage.get(id));
    }

    // 4. Update
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @Valid @RequestBody User updatedUser) {
        if (!userStorage.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Returns 404
        }
        
        updatedUser.setId(id); // Ensure the id remains unchanged
        userStorage.put(id, updatedUser);
        return ResponseEntity.ok(updatedUser); 
    }

    // 5. Delete 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        if (!userStorage.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
        }
        userStorage.remove(id);
        return ResponseEntity.noContent().build(); 
    }
}
