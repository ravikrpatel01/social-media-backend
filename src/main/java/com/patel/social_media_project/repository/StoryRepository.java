package com.patel.social_media_project.repository;

import com.patel.social_media_project.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Long> {
    public List<Story> findByUserId(Long userId);
}
