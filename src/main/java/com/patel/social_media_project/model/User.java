package com.patel.social_media_project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_id")
    private String email;

    @Column(name = "password")
    private String password;

    private String gender;

    @JsonIgnore
    @ManyToMany
    private List<Post> savedPost = new ArrayList<>();

    private List<Long> followers = new ArrayList<>();

    private List<Long> followings = new ArrayList<>();
}
