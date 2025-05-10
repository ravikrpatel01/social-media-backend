package com.patel.social_media_project.service;

import com.patel.social_media_project.exceptions.CommentNotFoundException;
import com.patel.social_media_project.exceptions.PostNotFoundException;
import com.patel.social_media_project.exceptions.UserNotFoundException;
import com.patel.social_media_project.model.Comment;

public interface CommentService {
    public Comment addComment(Comment comment, Long postId, Long userId) throws UserNotFoundException, PostNotFoundException;
    public Comment findCommentById(Long commentId) throws CommentNotFoundException;
    public Comment likeComment(Long commentId, Long userId) throws UserNotFoundException, CommentNotFoundException;
}
