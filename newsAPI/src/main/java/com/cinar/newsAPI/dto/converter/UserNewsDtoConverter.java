package com.cinar.newsAPI.dto.converter;

import com.cinar.newsAPI.dto.UsersNewsDto;
import com.cinar.newsAPI.model.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserNewsDtoConverter {
    private final NewsDtoConverter newsDtoConverter;

    public UserNewsDtoConverter(NewsDtoConverter newsDtoConverter) {
        this.newsDtoConverter = newsDtoConverter;
    }

    public UsersNewsDto convert(User from){
        return new UsersNewsDto(from.getUsername(), from.getEmail(),from.getNews().
                stream().map(newsDtoConverter::convert).collect(Collectors.toList()));
    }
}
