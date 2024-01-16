package com.cinar.newsAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
    @OneToMany(mappedBy = "news",fetch = FetchType.LAZY)
    private List<Comment> comments;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    public void addComments(Comment comment){
        if(comments==null) comments=new ArrayList<>();
        comments.add(comment);
    }
    public List<Comment> getComments() {
        return comments != null ? comments : Collections.emptyList();
    }
    public News(String title, String description, String photoPath, LocalDateTime createdTime) {
        this.title = title;
        this.description = description;
        this.photoPath = photoPath;
        this.createdTime = createdTime;
    }
}