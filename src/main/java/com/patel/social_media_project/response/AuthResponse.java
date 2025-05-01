package com.patel.social_media_project.response;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String message;
}
