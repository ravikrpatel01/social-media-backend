package com.patel.social_media_project.service;

import com.patel.social_media_project.model.Chat;
import com.patel.social_media_project.model.User;
import com.patel.social_media_project.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService{
    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat createChat(User reqUser, User user2) {
        Chat isExist = chatRepository.findChatByUsersId(user2, reqUser);

        if (isExist != null) {
            return isExist;
        }
        Chat newChat = new Chat();
        newChat.getUsers().add(user2);
        newChat.getUsers().add(reqUser);
        newChat.setTimestamp(LocalDateTime.now());

        return chatRepository.save(newChat);
    }

    @Override
    public Chat findChatById(Long chatId) throws Exception {
        Optional<Chat> chatOptional = chatRepository.findById(chatId);

        if (chatOptional.isEmpty()) {
            throw new Exception("Chat not found with ID: " + chatId);
        }
        return chatOptional.get();
    }

    @Override
    public List<Chat> findUsersChat(Long userId) {
        return chatRepository.findByUsersId(userId);
    }
}
