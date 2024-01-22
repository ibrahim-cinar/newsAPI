package com.cinar.newsAPI.controller;

import com.cinar.newsAPI.dto.*;
import com.cinar.newsAPI.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/news")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }
    @GetMapping()
    public ResponseEntity<List<NewsDto>>getAllNews(){
        return ResponseEntity.ok(newsService.getAllNews());
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<NewsDto> getNewsById(@PathVariable String id){
        return ResponseEntity.ok(newsService.getNewsById(id));
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<UsersNewsDto> getNewsByUsername(@PathVariable String username){
        return ResponseEntity.ok(newsService.getNewsByUsername(username));
    }
    @GetMapping("/latest/latest/")
    public ResponseEntity<List<NewsUserDto>>getLatestNews(){
        return ResponseEntity.ok(newsService.getLatestNews());
    }
    @GetMapping("/first/first/")
    public ResponseEntity<List<NewsUserDto>>getFirstNews(){
        return ResponseEntity.ok(newsService.getFirstNews());
    }

    @PostMapping("/news/{username}")
    public ResponseEntity<NewsDto> createNews(@RequestBody CreateNewsRequest createNewsRequest,@PathVariable String username){
        return ResponseEntity.ok(newsService.createNews(createNewsRequest,username));
    }
    @PutMapping("/{id}")
    public ResponseEntity<NewsDto> updateNews(@RequestBody UpdateNewsRequest updateNewsRequest,@PathVariable String id){
        return ResponseEntity.ok(newsService.updateNews(updateNewsRequest,id));
    }
}
