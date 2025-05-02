package com.patel.social_media_project.service;

import com.patel.social_media_project.model.Reels;
import com.patel.social_media_project.model.User;
import com.patel.social_media_project.repository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelsServiceImpl implements ReelsService{
    @Autowired
    private ReelsRepository reelsRepository;

    @Autowired
    private UserService userService;

    @Override
    public Reels createReels(Reels reels, User user) throws Exception {
        Reels newReels = new Reels();
        newReels.setTitle(reels.getTitle());
        newReels.setVideo(reels.getVideo());
        newReels.setUser(user);

        return reelsRepository.save(newReels);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUsersReel(Long userId) throws Exception {
        User user = userService.findUserById(userId);

        return reelsRepository.findByUserId(user.getId());
    }
}
