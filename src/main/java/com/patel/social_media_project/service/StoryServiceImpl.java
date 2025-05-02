package com.patel.social_media_project.service;

import com.patel.social_media_project.model.Story;
import com.patel.social_media_project.model.User;
import com.patel.social_media_project.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryServiceImpl implements StoryService {
    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserService userService;

    @Override
    public Story createStory(Story story, User user) {
        Story newStory = new Story();
        newStory.setCaptions(story.getCaptions());
        newStory.setUser(user);
        newStory.setImage(story.getImage());
        newStory.setCreatedAt(LocalDateTime.now());

        return storyRepository.save(newStory);
    }

    @Override
    public List<Story> findStoryByUserId(Long userId) throws Exception {
        User user = userService.findUserById(userId);
        return storyRepository.findByUserId(userId);
    }
}
