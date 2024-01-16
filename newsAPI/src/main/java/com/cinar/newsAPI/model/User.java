package com.cinar.newsAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    @NotBlank
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @Email
    private String email;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Comment> comments;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<News> news;

    public User(String username, String password, String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    public void addNews(News news1){
        if(news==null) news=new ArrayList<>();
        news.add(news1);
    }
    public List<News> getNews() {
        return Objects.requireNonNullElse(news, Collections.emptyList());
    }
    public void addComments(Comment comment){
        if(comments==null) comments=new ArrayList<>();
        comments.add(comment);
    }
    public List<Comment> getComments() {
        return Objects.requireNonNullElse(comments, Collections.emptyList());
    }
}
