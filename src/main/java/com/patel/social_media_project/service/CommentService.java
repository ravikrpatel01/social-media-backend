package com.patel.social_media_project.service;

import com.patel.social_media_project.model.Comment;

public interface CommentService {
    public Comment addComment(Comment comment, Long postId, Long userId) throws Exception;
    public Comment findCommentById(Long commentId) throws Exception;
    public Comment likeComment(Long commentId, Long userId) throws Exception;
}
