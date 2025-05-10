package com.patel.social_media_project.service;

import com.patel.social_media_project.exceptions.UserNotFoundException;
import com.patel.social_media_project.model.Reels;
import com.patel.social_media_project.model.User;

import java.util.List;

public interface ReelsService {
    public Reels createReels(Reels reels, User user) throws UserNotFoundException;
    public List<Reels> findAllReels();
    public List<Reels> findUsersReel(Long userId) throws UserNotFoundException;
}
