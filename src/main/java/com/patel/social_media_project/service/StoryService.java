package com.patel.social_media_project.service;

import com.patel.social_media_project.model.Story;
import com.patel.social_media_project.model.User;

import java.util.List;

public interface StoryService {
    public Story createStory(Story story, User user);
    public List<Story> findStoryByUserId(Long userId) throws Exception;
}
