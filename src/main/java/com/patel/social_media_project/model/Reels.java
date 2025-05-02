package com.patel.social_media_project.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reels")
@Data
public class Reels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String video;

    @ManyToOne
    private User user;
}
