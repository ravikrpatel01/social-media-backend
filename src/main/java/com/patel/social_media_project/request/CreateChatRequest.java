package com.patel.social_media_project.request;

import com.patel.social_media_project.model.User;
import lombok.Data;

@Data
public class CreateChatRequest {
    private User reqUser;
    private User user2;
}
