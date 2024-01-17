package com.cinar.newsAPI.controller;

import com.cinar.newsAPI.dto.CommentDto;
import com.cinar.newsAPI.dto.NewsDto;
import com.cinar.newsAPI.dto.UsersNewsDto;
import com.cinar.newsAPI.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{username}/{newsId}")
    public ResponseEntity<UsersNewsDto>getNewsCommentsByUsername(@PathVariable String username,@PathVariable String newsId){
        return ResponseEntity.ok(commentService.getNewsCommentsByUsername(username, newsId));
    }
    @PostMapping("/post/{username}/{newsId}")
    public ResponseEntity<UsersNewsDto>addCommentToNews( @PathVariable String username, @PathVariable String newsId,@RequestBody CommentDto comment){
        return ResponseEntity.ok(commentService.addCommentToNews(username,newsId,comment));
    }
    @PutMapping("/update/{username}/{newsId}/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable String username,@PathVariable String newsId
            ,@PathVariable String commentId,@RequestBody CommentDto comment){
        return ResponseEntity.ok(commentService.updateComment(username, newsId, commentId, comment));
    }
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<CommentDto> deleteComment(@PathVariable String id){
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }
}
