package com.patel.social_media_project.controller;

import com.patel.social_media_project.exceptions.ChatNotFoundException;
import com.patel.social_media_project.exceptions.UserNotFoundException;
import com.patel.social_media_project.model.Chat;
import com.patel.social_media_project.model.Message;
import com.patel.social_media_project.model.User;
import com.patel.social_media_project.service.ChatService;
import com.patel.social_media_project.service.MessageService;
import com.patel.social_media_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    @PostMapping("/chat/{chatId}")
    public ResponseEntity<Message> createMessage(
            @RequestHeader("Authorization") String jwt,
            @RequestBody Message req,
            @PathVariable Long chatId
    ) throws UserNotFoundException, ChatNotFoundException {
        User user = userService.findUserFromJwtToken(jwt);
        Message message = messageService.createMessage(user, chatId, req);

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<Message>> findChatMessages(
         @PathVariable Long chatId
    ) throws ChatNotFoundException {
        Chat chat = chatService.findChatById(chatId);
        List<Message> messages = messageService.findChatMessages(chat.getId());

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
