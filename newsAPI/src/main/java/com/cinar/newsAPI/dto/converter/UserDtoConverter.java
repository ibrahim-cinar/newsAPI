package com.cinar.newsAPI.dto.converter;

import com.cinar.newsAPI.dto.UserDto;
import com.cinar.newsAPI.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {
    public UserDto convert(User from){
        return new UserDto(from.getUsername(),
                from.getFirstName(),
                from.getLastName(),
                from.getEmail());
    }
}
