package com.patel.social_media_project.service;

import com.patel.social_media_project.exceptions.ChatNotFoundException;
import com.patel.social_media_project.exceptions.UserNotFoundException;
import com.patel.social_media_project.model.Message;
import com.patel.social_media_project.model.User;

import java.util.List;

public interface MessageService {
    public Message createMessage(User user, Long chatId, Message req) throws UserNotFoundException, ChatNotFoundException;
    public List<Message> findChatMessages(Long chatId) throws ChatNotFoundException;
}
