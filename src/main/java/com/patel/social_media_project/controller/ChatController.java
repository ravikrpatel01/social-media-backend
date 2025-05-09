package com.patel.social_media_project.controller;

import com.patel.social_media_project.model.Chat;
import com.patel.social_media_project.request.CreateChatRequest;
import com.patel.social_media_project.service.ChatService;
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

    @PostMapping
    public ResponseEntity<Chat> createChat(
            @RequestBody CreateChatRequest request
            ) {
        Chat createdChat = chatService.createChat(request.getReqUser(), request.getUser2());
        return new ResponseEntity<>(createdChat, HttpStatus.CREATED);
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<Chat> findChatById(
            @PathVariable Long chatId
    ) throws Exception {
        Chat chat = chatService.findChatById(chatId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Chat>> findUsersChat(
            @PathVariable Long userId
    ) {
        List<Chat> chats = chatService.findUsersChat(userId);
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }
}
