package com.patel.social_media_project.controller;

import com.patel.social_media_project.model.User;
import com.patel.social_media_project.repository.UserRepository;
import com.patel.social_media_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User savedUser = userService.registerUser(user);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) throws Exception {
        User user = userService.findUserById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) throws Exception {
        User updatedUser = userService.updateUser(user, id);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PutMapping("/update-password/{id}")
    public ResponseEntity<User> updatePassword(@PathVariable Long id, @RequestBody User user) throws Exception {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new Exception("User not found with id: " + id);
        }
        String password = user.getPassword();
        User updatedUser = userService.updatePassword(userOptional.get(), password);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PutMapping("/follow/{userId1}/{userId2}")
    public ResponseEntity<User> followUserHandler(@PathVariable Long userId1, @PathVariable Long userId2) throws Exception {
        User user = userService.followUser(userId1, userId2);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUser(@RequestParam String query) {
        return new ResponseEntity<>(userService.searchUser(query), HttpStatus.OK);
    }
}
