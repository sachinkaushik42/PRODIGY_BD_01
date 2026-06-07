package com.example.demo.controller;

import com.example.demo.model.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/users")
public class Controller {


    private final Map<Long, User> storage = new ConcurrentHashMap<>();
    
    //Handles incrementation as 1,2,3...
    private final AtomicLong counter = new AtomicLong(1);

    // Loads initial data 
    @jakarta.annotation.PostConstruct
    public void loadInitialData() {
        User sysUser = new User();
        Long firstId = counter.getAndIncrement(); // Gets 1
        
        sysUser.setId(firstId);
        sysUser.setName("Sachin Kaushik");
        sysUser.setEmail("sachinkaushik42567@gmail.com");
        sysUser.setAge(23);
        
        storage.put(firstId, sysUser);
    }

    // Create   (Post)
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        Long newId = counter.getAndIncrement(); // Automatically assigns the next number like 2,3...
        user.setId(newId); 
        storage.put(newId, user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    // Read All 
    @GetMapping
    public ResponseEntity<Collection<User>> getAllUsers() {
        return ResponseEntity.ok(storage.values());
    }

    // 3. Read one by one id 
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        if (!storage.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(storage.get(id));
    }

    // 4. Update 
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User updatedUser) {
        if (!storage.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        updatedUser.setId(id); // Keep the ID the same
        storage.put(id, updatedUser);
        return ResponseEntity.ok(updatedUser);
    }

    // 5. Delete 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!storage.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        storage.remove(id);
        return ResponseEntity.noContent().build();
    }
}
