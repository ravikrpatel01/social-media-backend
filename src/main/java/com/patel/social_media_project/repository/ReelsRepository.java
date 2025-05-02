package com.patel.social_media_project.repository;

import com.patel.social_media_project.model.Reels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReelsRepository extends JpaRepository<Reels, Long> {
    public List<Reels> findByUserId(Long userId);
}
