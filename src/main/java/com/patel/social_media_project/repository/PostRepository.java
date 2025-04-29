package com.patel.social_media_project.repository;

import com.patel.social_media_project.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p from Post p where p.user.id = :userId")
    public List<Post> findPostByUserId(Long userId);
}
