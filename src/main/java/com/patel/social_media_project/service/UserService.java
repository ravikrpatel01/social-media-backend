package com.patel.social_media_project.service;

import com.patel.social_media_project.exceptions.UserNotFoundException;
import com.patel.social_media_project.model.User;

import java.util.List;

public interface UserService {
    public User findUserById(Long userId) throws UserNotFoundException;
    public User findUserByEmail(String email) throws UserNotFoundException;
    public User followUser(Long userId1, Long userId2) throws UserNotFoundException;
    public String deleteUser(Long userId) throws UserNotFoundException;
    public User updateUser(User user, Long userId) throws UserNotFoundException;
    public User updatePassword(User user, String password);
    public List<User> searchUser(String query);
    public List<User> getAllUsers();
    public User findUserFromJwtToken(String jwt) throws UserNotFoundException;
}
