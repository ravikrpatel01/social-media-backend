package com.patel.social_media_project.controller;

import com.patel.social_media_project.model.Post;
import com.patel.social_media_project.response.ResponseMessage;
import com.patel.social_media_project.service.PostService;
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

    @PostMapping("/user/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Long userId) throws Exception {
        Post createdPost = postService.createPost(post, userId);

        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAllPost() {
        List<Post> posts = postService.findAllPost();

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{postId}/{userId}")
    public ResponseEntity<ResponseMessage> deletePost(@PathVariable Long postId, @PathVariable Long userId) throws Exception {
        String response = postService.deletePost(postId, userId);
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(response);
        responseMessage.setStatus(true);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable Long postId) throws Exception {
        Post post = postService.findPostById(postId);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> findPostByUserId(@PathVariable Long userId) {
        List<Post> posts = postService.findPostByUserId(userId);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PutMapping("/{postId}/user/{userId}")
    public ResponseEntity<Post> savedPost(@PathVariable Long postId, @PathVariable Long userId) throws Exception {
        Post post = postService.savedPost(postId, userId);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("/like/{postId}/user/{userId}")
    public ResponseEntity<Post> likedPost(@PathVariable Long postId, @PathVariable Long userId) throws Exception {
        Post post = postService.likedPost(postId, userId);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}
