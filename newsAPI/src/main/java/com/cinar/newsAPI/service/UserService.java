package com.cinar.newsAPI.service;

import com.cinar.newsAPI.dto.UserDto;
import com.cinar.newsAPI.dto.UsersNewsDto;
import com.cinar.newsAPI.dto.converter.UserDtoConverter;
import com.cinar.newsAPI.dto.converter.UserNewsDtoConverter;
import com.cinar.newsAPI.exception.EmailNotFoundException;
import com.cinar.newsAPI.exception.UserNotFoundException;
import com.cinar.newsAPI.model.User;
import com.cinar.newsAPI.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;
    private final UserNewsDtoConverter userNewsDtoConverter;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter, UserNewsDtoConverter userNewsDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
        this.userNewsDtoConverter = userNewsDtoConverter;
    }
    public List<UserDto> getAllUser(){
        return userRepository.findAll().stream().
                map(userDtoConverter::convert).collect(Collectors.toList());
    }


    protected User findUserById(String id){
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User could not find by id "+id));
    }
    public UserDto getUserById(String id){
        return userDtoConverter.convert(findUserById(id));
    }


    protected Optional<User>findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }
    public UserDto getUserByUsername(String username){
        var usernameNews = findUserByUsername(username).orElseThrow(()->new UserNotFoundException("User could not find by username "+username));

        return userDtoConverter.convert(usernameNews);
    }

    public UsersNewsDto getNewsByUsername(String username){
        var usernameNews = findUserByUsername(username).orElseThrow(()->new UserNotFoundException("Users News could not find by username "+username));
        return userNewsDtoConverter.convert(usernameNews);
    }


    protected Optional<User> findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
    public UserDto getUserByEmail(String email){
        var userEmail = findUserByEmail(email).orElseThrow(()->new EmailNotFoundException("User could not find by email "+email));
        return userDtoConverter.convert(userEmail);
    }
}
