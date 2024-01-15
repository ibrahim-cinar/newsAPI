package com.cinar.newsAPI.repository;

import com.cinar.newsAPI.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News,String> {
}
