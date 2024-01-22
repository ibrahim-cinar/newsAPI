package com.cinar.newsAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")

public class User implements UserDetails {
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
    private boolean accountNonExpired;
    private boolean isEnabled;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Comment> comments;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<News> news;

    @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)
    @JoinTable(name = "roles",joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Role> authorities;

    public User(String username, String password, String firstName,
                String lastName, String email, boolean accountNonExpired,
                boolean isEnabled, boolean accountNonLocked,
                boolean credentialsNonExpired, List<Role> authorities) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.accountNonExpired = accountNonExpired;
        this.isEnabled = isEnabled;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.authorities = authorities;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String firstName, String lastName, String email, List<Role> authorities) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.authorities = authorities;
    }


    public void addNews(News news1){
        if(news==null) news=new ArrayList<>();
        news.add(news1);
    }
    public List<News> getNews() {
        return Objects.requireNonNullElse(news, Collections.emptyList());
    }



}
