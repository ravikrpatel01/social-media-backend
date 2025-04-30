package com.patel.social_media_project.service;

import com.patel.social_media_project.model.User;

import java.util.List;

public interface UserService {
    public User findUserById(Long userId) throws Exception;
    public User findUserByEmail(String email) throws Exception;
    public User followUser(Long userId1, Long userId2) throws Exception;
    public User updateUser(User user, Long userId) throws Exception;
    public User updatePassword(User user, String password);
    public List<User> searchUser(String query);
    public List<User> getAllUsers();
}
