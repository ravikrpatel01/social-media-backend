package com.patel.social_media_project.controller;

import com.patel.social_media_project.exceptions.ChatNotFoundException;
import com.patel.social_media_project.exceptions.UserNotFoundException;
import com.patel.social_media_project.model.Chat;
import com.patel.social_media_project.model.User;
import com.patel.social_media_project.request.CreateChatRequest;
import com.patel.social_media_project.service.ChatService;
import com.patel.social_media_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Chat> createChat(
            @RequestHeader("Authorization") String jwt,
            @RequestBody CreateChatRequest request
    ) throws UserNotFoundException {
        User reqUser = userService.findUserFromJwtToken(jwt);
        User user2 = userService.findUserById(request.getUserId());
        Chat createdChat = chatService.createChat(reqUser, user2);
        return new ResponseEntity<>(createdChat, HttpStatus.CREATED);
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<Chat> findChatById(
            @PathVariable Long chatId
    ) throws ChatNotFoundException {
        Chat chat = chatService.findChatById(chatId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Chat>> findUsersChat(
            @RequestHeader("Authorization") String jwt
    ) throws UserNotFoundException {
        User user = userService.findUserFromJwtToken(jwt);
        List<Chat> chats = chatService.findUsersChat(user.getId());
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }
}
