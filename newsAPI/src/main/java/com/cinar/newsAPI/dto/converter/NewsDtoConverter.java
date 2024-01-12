package com.cinar.newsAPI.dto.converter;

import com.cinar.newsAPI.dto.NewsDto;
import com.cinar.newsAPI.model.News;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class NewsDtoConverter {

    private final CommentDtoConverter commentDtoConverter;

    public NewsDtoConverter(CommentDtoConverter commentDtoConverter) {
        this.commentDtoConverter = commentDtoConverter;
    }

    public NewsDto convert(News from){
        return new NewsDto(from.getDescription(), from.getTitle(), from.getCreatedTime(), from.getPhotoPath(),
                from.getComments().stream().map(commentDtoConverter::convert).collect(Collectors.toList()));
    }
}
