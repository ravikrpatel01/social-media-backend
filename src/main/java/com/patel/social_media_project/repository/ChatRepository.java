package com.patel.social_media_project.repository;

import com.patel.social_media_project.model.Chat;
import com.patel.social_media_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    public List<Chat> findByUsersId(Long userId);

    @Query("Select c FROM Chat c WHERE :user Member of c.users AND :reqUser Member of c.users")
    public Chat findChatByUsersId(@Param("user") User user, @Param("reqUser") User reqUser);
}
