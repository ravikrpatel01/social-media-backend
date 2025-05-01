package com.patel.social_media_project.service;

import com.patel.social_media_project.model.Post;
import com.patel.social_media_project.model.User;
import com.patel.social_media_project.repository.PostRepository;
import com.patel.social_media_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Post createPost(Post post, Long userId) throws Exception {
        User user = userService.findUserById(userId);

        Post newPost = new Post();

        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setVideo(post.getVideo());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setUser(user);

        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(Long postId, Long userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (!Objects.equals(post.getUser().getId(), user.getId())) {
            throw new Exception("You can't delete this post.");
        }

        postRepository.deleteById(postId);
        return "Post deleted successfully!";
    }

    @Override
    public List<Post> findPostByUserId(Long userId) {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(Long postId) throws Exception {
        Optional<Post> postOptional = postRepository.findById(postId);

        if (postOptional.isEmpty()) {
            throw new Exception("Post not found with id: " + postId);
        }

        return postOptional.get();
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Long postId, Long userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (user.getSavedPost().contains(post)) {
            user.getSavedPost().remove(post);
        } else {
            user.getSavedPost().add(post);
        }
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likedPost(Long postId, Long userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (post.getLiked().contains(user)) {
            post.getLiked().remove(user);
        } else {
            post.getLiked().add(user);
        }

        return postRepository.save(post);
    }
}
