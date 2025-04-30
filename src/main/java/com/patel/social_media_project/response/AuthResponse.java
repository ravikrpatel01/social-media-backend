package com.patel.social_media_project.response;

import lombok.Data;

@Data
public class AuthResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String token;
    private String message;
}
