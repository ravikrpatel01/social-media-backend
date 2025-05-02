package com.patel.social_media_project.controller;

import com.patel.social_media_project.model.Reels;
import com.patel.social_media_project.model.User;
import com.patel.social_media_project.service.ReelsService;
import com.patel.social_media_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reels")
public class ReelsController {
    @Autowired
    private ReelsService reelsService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Reels> createReels(
            @RequestBody Reels reels,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserFromJwtToken(jwt);
        Reels createdReel = reelsService.createReels(reels, user);

        return new ResponseEntity<>(createdReel, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Reels>> findAllReels() {
        List<Reels> reels = reelsService.findAllReels();

        return new ResponseEntity<>(reels, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reels>> findUserReels(
            @PathVariable Long userId
    ) throws Exception {
        List<Reels> reels = reelsService.findUsersReel(userId);

        return new ResponseEntity<>(reels, HttpStatus.OK);
    }
}
