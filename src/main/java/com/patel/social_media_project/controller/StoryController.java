package com.patel.social_media_project.controller;

import com.patel.social_media_project.model.Story;
import com.patel.social_media_project.model.User;
import com.patel.social_media_project.service.StoryService;
import com.patel.social_media_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
public class StoryController {
    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Story> createStory(
            @RequestBody Story story,
            @RequestHeader("Authorization") String jwt
    ) {
        User user = userService.findUserFromJwtToken(jwt);
        Story createdStory = storyService.createStory(story, user);

        return new ResponseEntity<>(createdStory, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Story>> findStoryByUserId(@PathVariable Long userId) throws Exception {
        List<Story> stories = storyService.findStoryByUserId(userId);

        return new ResponseEntity<>(stories, HttpStatus.OK);
    }
}
