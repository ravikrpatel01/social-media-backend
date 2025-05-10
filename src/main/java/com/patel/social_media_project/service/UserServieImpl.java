package com.patel.social_media_project.service;

import com.patel.social_media_project.config.JwtProvider;
import com.patel.social_media_project.model.User;
import com.patel.social_media_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServieImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new Exception("User not found with ID: " + userId);
        }
        return userOptional.get();
    }

    @Override
    public User findUserByEmail(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return user;
    }

    @Override
    public User followUser(Long reqUserId, Long userId2) throws Exception {
        User reqUser = findUserById(reqUserId);
        User user2 = findUserById(userId2);

        user2.getFollowers().add(reqUser.getId());
        reqUser.getFollowings().add(user2.getId());

        userRepository.save(user2);
        return userRepository.save(reqUser);
    }

    @Override
    public String deleteUser(Long userId) throws Exception {
        User user = findUserById(userId);
        userRepository.deleteById(user.getId());
        return "User deleted successfully!";
    }

    @Override
    public User updateUser(User user, Long userId) throws Exception {
        User user1 = findUserById(userId);

        if (user.getFirstName() != null && !user.getFirstName().equals(user1.getFirstName())) {
            user1.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null && !user.getLastName().equals(user1.getLastName())) {
            user1.setLastName(user.getLastName());
        }
        if (user.getEmail() != null && !user.getEmail().equals(user1.getEmail())) {
            user1.setEmail(user.getEmail());
        }
        if (user.getGender() != null && !user.getGender().equals(user1.getGender())) {
            user1.setGender(user.getGender());
        }

        return userRepository.save(user1);
    }

    @Override
    public User updatePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserFromJwtToken(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);

        return findUserByEmail(email);
    }
}
