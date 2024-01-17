package com.cinar.newsAPI.service;

import com.cinar.newsAPI.dto.CommentDto;
import com.cinar.newsAPI.dto.UsersNewsDto;
import com.cinar.newsAPI.dto.converter.CommentDtoConverter;
import com.cinar.newsAPI.dto.converter.UsersNewsDtoConverter;
import com.cinar.newsAPI.exception.CommentNotFoundException;
import com.cinar.newsAPI.exception.NewsNotFoundException;
import com.cinar.newsAPI.exception.UserNotFoundException;
import com.cinar.newsAPI.model.Comment;
import com.cinar.newsAPI.model.News;
import com.cinar.newsAPI.model.User;
import com.cinar.newsAPI.repository.CommentRepository;
import com.cinar.newsAPI.repository.NewsRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class CommentService {

    private  final CommentRepository commentRepository;
    private final UserService userService;
    private final UsersNewsDtoConverter usersNewsDtoConverter;
    private final CommentDtoConverter commentDtoConverter;

    public CommentService(CommentRepository commentRepository,UserService userService, UsersNewsDtoConverter usersNewsDtoConverter, CommentDtoConverter commentDtoConverter) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.usersNewsDtoConverter = usersNewsDtoConverter;
        this.commentDtoConverter = commentDtoConverter;
    }

    public UsersNewsDto getNewsCommentsByUsername(String username, String newsId) {
        User user = userService.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found" + username));

        News news = user.getNews().stream()
                .filter(n -> n.getNewsId().equals(newsId))
                .findFirst()
                .orElseThrow(() -> new NewsNotFoundException("News not found"));

        return usersNewsDtoConverter.convert(news);
    }
    public UsersNewsDto addCommentToNews(String username, String newsId, @NotNull CommentDto comment){
        var user = userService.findUserByUsername(username)
                .orElseThrow(()-> new UserNotFoundException("User not found"+username));
        var news = user.getNews().stream()
                .filter(news1 -> news1.getNewsId().equals(newsId))
                .findFirst()
                .orElseThrow(()-> new NewsNotFoundException("News not found "+newsId));
        var comments = new Comment();
        comments.setText(comment.getText());
        comments.setCommentTime(LocalDateTime.now());
        comments.setUser(user);
        news.addComments(comments);
        comments.setNews(news);

        commentRepository.save(comments);
        return usersNewsDtoConverter.convert(news);
    }
    public CommentDto updateComment(String username,String newsId,String commentId,CommentDto comments){
        var user = userService.findUserByUsername(username).orElseThrow(()->new UserNotFoundException("User not found"+username));
        var news = user.getNews().stream()
                .filter(news1 -> news1.getNewsId().equals(newsId))
                .findFirst()
                .orElseThrow(()-> new NewsNotFoundException("News not found"+newsId));
        var comment = news.getComments().stream()
                .filter(c->c.getCommentId().equals(commentId))
                .findFirst()
                .orElseThrow(()->new CommentNotFoundException("Comment not found"+commentId));

        comment.setText(comments.getText());
        comment.setCommentTime(LocalDateTime.now());
        commentRepository.save(comment);
        return commentDtoConverter.convert(comment);

    }

    public void deleteComment(String id){
        if(doesCommentExist(id)){
            commentRepository.deleteById(id);
            throw new ResponseStatusException(HttpStatus.OK, "comment deleted");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "comment no found");
    }
    private  boolean doesCommentExist(String id){
        return commentRepository.existsById(id);
    }
}
