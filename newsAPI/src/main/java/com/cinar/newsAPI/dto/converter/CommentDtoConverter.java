package com.cinar.newsAPI.dto.converter;

import com.cinar.newsAPI.dto.CommentDto;
import com.cinar.newsAPI.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentDtoConverter {
    public CommentDto convert(Comment from){
        return  new CommentDto(from.getText(),from.getCommentTime());
    }
}
