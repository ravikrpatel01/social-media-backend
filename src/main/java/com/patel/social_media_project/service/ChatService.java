package com.patel.social_media_project.service;

import com.patel.social_media_project.exceptions.ChatNotFoundException;
import com.patel.social_media_project.model.Chat;
import com.patel.social_media_project.model.User;

import java.util.List;

public interface ChatService {
    public Chat createChat(User reqUser, User user2);
    public Chat findChatById(Long chatId) throws ChatNotFoundException;
    public List<Chat> findUsersChat(Long userId);
}
