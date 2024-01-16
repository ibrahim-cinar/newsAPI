package com.cinar.newsAPI.repository;

import com.cinar.newsAPI.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,String> {
    List<News> findAllByOrderByCreatedTimeDesc();
    List<News> findAllByOrderByCreatedTimeAsc();
}
