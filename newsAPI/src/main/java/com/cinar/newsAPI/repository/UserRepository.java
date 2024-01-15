package com.cinar.newsAPI.repository;

import com.cinar.newsAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);
}
