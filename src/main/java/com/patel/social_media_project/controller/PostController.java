package com.patel.social_media_project.controller;

import com.patel.social_media_project.exceptions.PostNotFoundException;
import com.patel.social_media_project.exceptions.UserNotFoundException;
import com.patel.social_media_project.model.Post;
import com.patel.social_media_project.model.User;
import com.patel.social_media_project.response.ResponseMessage;
import com.patel.social_media_project.service.PostService;
import com.patel.social_media_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<Post> createPost(
            @RequestBody Post post,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User reqUser = userService.findUserFromJwtToken(jwt);
        Post createdPost = postService.createPost(post, reqUser.getId());

        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAllPost() {
        List<Post> posts = postService.findAllPost();

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<ResponseMessage> deletePost(
            @PathVariable Long postId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User reqUser = userService.findUserFromJwtToken(jwt);

        String response = postService.deletePost(postId, reqUser.getId());

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(response);
        responseMessage.setStatus(true);

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> findPostById(
            @PathVariable Long postId
    ) throws Exception {
        Post post = postService.findPostById(postId);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> findPostByUserId(
            @PathVariable Long userId
    ) {
        List<Post> posts = postService.findPostByUserId(userId);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PutMapping("/save/{postId}")
    public ResponseEntity<Post> savedPost(
            @PathVariable Long postId,
            @RequestHeader("Authorization") String jwt
    ) throws UserNotFoundException, PostNotFoundException {
        User reqUser = userService.findUserFromJwtToken(jwt);
        Post post = postService.savedPost(postId, reqUser.getId());

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("/like/{postId}")
    public ResponseEntity<Post> likedPost(
            @PathVariable Long postId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User reqUser = userService.findUserFromJwtToken(jwt);
        Post post = postService.likedPost(postId, reqUser.getId());

        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}
