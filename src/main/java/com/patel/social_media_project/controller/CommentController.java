package com.patel.social_media_project.controller;

import com.patel.social_media_project.model.Comment;
import com.patel.social_media_project.model.User;
import com.patel.social_media_project.service.CommentService;
import com.patel.social_media_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}")
    public ResponseEntity<Comment> addComment(
           @RequestBody Comment comment,
           @PathVariable Long postId,
           @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User reqUser = userService.findUserFromJwtToken(jwt);
        Comment addedComment = commentService.addComment(comment, postId, reqUser.getId());

        return new ResponseEntity<>(addedComment, HttpStatus.CREATED);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> findCommentById(
            @PathVariable Long commentId
    ) throws Exception {
        Comment comment = commentService.findCommentById(commentId);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PutMapping("/like/{commentId}")
    public ResponseEntity<Comment> likeComment(
            @PathVariable Long commentId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User reqUser = userService.findUserFromJwtToken(jwt);

        Comment likedComment = commentService.likeComment(commentId, reqUser.getId());

        return new ResponseEntity<>(likedComment, HttpStatus.OK);
    }
}
