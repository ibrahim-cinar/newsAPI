package com.cinar.newsAPI.dto.converter;

import com.cinar.newsAPI.dto.NewsUserDto;
import com.cinar.newsAPI.dto.UsersNewsDto;
import com.cinar.newsAPI.model.News;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class NewsUserDtoConverter {
    private final CommentDtoConverter commentDtoConverter;
    private final UserDtoConverter userDtoConverter;

    public NewsUserDtoConverter(CommentDtoConverter commentDtoConverter, UserDtoConverter userDtoConverter) {
        this.commentDtoConverter = commentDtoConverter;
        this.userDtoConverter = userDtoConverter;
    }
    public NewsUserDto convert(News from){
        return new NewsUserDto(from.getDescription(), from.getTitle(),
                from.getCreatedTime(),from.getPhotoPath(),
                from.getComments().stream().map(commentDtoConverter::convert).collect(Collectors.toList()),
                userDtoConverter.convert(from.getUser())
        );
    }
}
