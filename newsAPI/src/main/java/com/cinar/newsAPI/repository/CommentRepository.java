package com.cinar.newsAPI.repository;

import com.cinar.newsAPI.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,String> {
}
