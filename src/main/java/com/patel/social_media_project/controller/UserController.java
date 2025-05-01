package com.patel.social_media_project.controller;

import com.patel.social_media_project.model.User;
import com.patel.social_media_project.repository.UserRepository;
import com.patel.social_media_project.request.UpdatePasswordRequest;
import com.patel.social_media_project.response.ResponseMessage;
import com.patel.social_media_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) throws Exception {
        User user = userService.findUserById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ResponseMessage> deleteUser(@PathVariable Long userId) throws Exception {
        String response = userService.deleteUser(userId);
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(response);
        responseMessage.setStatus(true);

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(
            @RequestBody User user,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserFromJwtToken(jwt);

        User updatedUser = userService.updateUser(user, reqUser.getId());

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PutMapping("/update-password")
    public ResponseEntity<User> updatePassword(
            @RequestHeader("Authorization") String jwt,
            @RequestBody UpdatePasswordRequest req
            ) throws Exception {
        User reqUser = userService.findUserFromJwtToken(jwt);

        String password = req.getPassword();
        User updatedUser = userService.updatePassword(reqUser, password);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PutMapping("/follow/{userId2}")
    public ResponseEntity<User> followUserHandler(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long userId2
    ) throws Exception {
        User reqUser = userService.findUserFromJwtToken(jwt);
        User user = userService.followUser(reqUser.getId(), userId2);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUser(@RequestParam String query) {
        return new ResponseEntity<>(userService.searchUser(query), HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserFromToken(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserFromJwtToken(jwt);
        user.setPassword(null);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
