package com.patel.social_media_project.service;

import com.patel.social_media_project.exceptions.CommentNotFoundException;
import com.patel.social_media_project.exceptions.PostNotFoundException;
import com.patel.social_media_project.exceptions.UserNotFoundException;
import com.patel.social_media_project.model.Comment;
import com.patel.social_media_project.model.Post;
import com.patel.social_media_project.model.User;
import com.patel.social_media_project.repository.CommentRepository;
import com.patel.social_media_project.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment addComment(Comment comment, Long postId, Long userId) throws UserNotFoundException, PostNotFoundException {
        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);

        Comment newComment = new Comment();
        newComment.setContent(comment.getContent());
        newComment.setUser(user);
        newComment.setCreatedAt(LocalDateTime.now());

        post.getComments().add(newComment);
        postRepository.save(post);

        return commentRepository.save(newComment);
    }

    @Override
    public Comment findCommentById(Long commentId) throws CommentNotFoundException {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);

        if (commentOptional.isEmpty()) {
            throw new CommentNotFoundException("Comment not found with id: " + commentId);
        }
        return commentOptional.get();
    }

    @Override
    public Comment likeComment(Long commentId, Long userId) throws UserNotFoundException, CommentNotFoundException {
        Comment comment = findCommentById(commentId);
        User user = userService.findUserById(userId);

        if (!comment.getLiked().contains(user)) {
            comment.getLiked().add(user);
        } else {
            comment.getLiked().remove(user);
        }
        return commentRepository.save(comment);
    }
}
