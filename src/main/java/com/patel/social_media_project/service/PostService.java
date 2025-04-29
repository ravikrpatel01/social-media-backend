package com.patel.social_media_project.service;

import com.patel.social_media_project.model.Post;

import java.util.List;

public interface PostService {
    public Post createPost(Post post, Long userId) throws Exception;
    public String deletePost(Long postId, Long userId) throws Exception;
    public List<Post> findPostByUserId(Long userId);
    public Post findPostById(Long postId) throws Exception;
    public List<Post> findAllPost();
    public Post savedPost(Long postId, Long userId) throws Exception;
    public Post likedPost(Long postId, Long userId) throws Exception;
}
