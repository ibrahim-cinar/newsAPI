package com.cinar.newsAPI.dto.converter;

import com.cinar.newsAPI.dto.UsersNewsDto;
import com.cinar.newsAPI.model.News;
import com.cinar.newsAPI.model.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class UsersNewsDtoConverter {
    private final NewsDtoConverter newsDtoConverter;

    public UsersNewsDtoConverter(NewsDtoConverter newsDtoConverter) {
        this.newsDtoConverter = newsDtoConverter;
    }

    public UsersNewsDto convert(News from){
        return new UsersNewsDto(from.getUser().getUsername(),
                Collections.singletonList(newsDtoConverter.convert(from)));
    }

}
