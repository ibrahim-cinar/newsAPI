package com.cinar.newsAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String newsId;
    private String title;
    private String description;
    private String photoPath;
    private LocalDateTime createdTime;
    @OneToMany(mappedBy = "news",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Comment> comments;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

}