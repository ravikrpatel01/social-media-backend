package com.patel.social_media_project.service;

import com.patel.social_media_project.model.Chat;
import com.patel.social_media_project.model.Message;
import com.patel.social_media_project.model.User;
import com.patel.social_media_project.repository.ChatRepository;
import com.patel.social_media_project.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatService chatService;

    @Override
    public Message createMessage(User user, Long chatId, Message req) throws Exception {
        Message message = new Message();

        Chat chat = chatService.findChatById(chatId);

        message.setChat(chat);
        message.setUser(user);
        message.setImage(req.getImage());
        message.setContent(req.getContent());
        message.setTimestamp(LocalDateTime.now());

        Message savedMessage = messageRepository.save(message);

        chat.getMessages().add(savedMessage);
        chatRepository.save(chat);

        return savedMessage;
    }

    @Override
    public List<Message> findChatMessages(Long chatId) {
        return messageRepository.findByChatId(chatId);
    }
}
