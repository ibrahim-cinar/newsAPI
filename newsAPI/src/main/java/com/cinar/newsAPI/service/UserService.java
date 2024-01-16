package com.cinar.newsAPI.service;

import com.cinar.newsAPI.dto.CreateUserRequest;
import com.cinar.newsAPI.dto.UpdateUserRequest;
import com.cinar.newsAPI.dto.UserDto;
import com.cinar.newsAPI.dto.UsersNewsDto;
import com.cinar.newsAPI.dto.converter.UserDtoConverter;
import com.cinar.newsAPI.dto.converter.UserNewsDtoConverter;
import com.cinar.newsAPI.exception.*;
import com.cinar.newsAPI.model.User;
import com.cinar.newsAPI.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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


    protected Optional<User> findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
    public UserDto getUserByEmail(String email){
        var userEmail = findUserByEmail(email).orElseThrow(()->new EmailNotFoundException("User could not find by email "+email));
        return userDtoConverter.convert(userEmail);
    }
    public UserDto createNewUser(CreateUserRequest createUserRequest){
        if(isEmailUnique(createUserRequest.getEmail())){
            if(isUsernameUnique(createUserRequest.getUsername())){
                User user = createUserFromRequest(createUserRequest);
                return userDtoConverter.convert(userRepository.save(user));
            }
            else    throw new UsernameAlreadyExistsException("Username already exists");
        }
        else     throw new EmailAlreadyExistsException("Email already exists");

    }
    private boolean isEmailUnique(String email){
        Optional<User> existingUserEmail = userRepository.findUserByEmail(email);
        return existingUserEmail.isEmpty();
    }
    private boolean isUsernameUnique(String username){
        Optional<User> existingUserUsername = userRepository.findUserByUsername(username);
        return existingUserUsername.isEmpty();
    }
    public static User createUserFromRequest(CreateUserRequest request){
        if(isInputValid(request)){
            return new User(request.getUsername(), request.getPassword(), request.getFirstName(), request.getLastName(),request.getEmail());
        }
        else throw new InvalidInputException("Invalid input data");
    }
    private static boolean isInputValid(CreateUserRequest request) {
        return request.getUsername() != null &&
                request.getFirstName() != null &&
                request.getLastName() != null &&
                request.getEmail() != null &&
                request.getPassword() != null;
    }
    public UserDto updateUser(String email, UpdateUserRequest updateUserRequest) {
        User user = findUserByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException("Email not found: " + email));

        validateUniqueFields(user, updateUserRequest);

        user.setUsername(updateUserRequest.getUsername());
        user.setPassword(updateUserRequest.getPassword());
        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        user.setEmail(updateUserRequest.getEmail());

        User updatedUser = userRepository.save(user);
        return userDtoConverter.convert(updatedUser);
    }

    private void validateUniqueFields(User user, UpdateUserRequest updateUserRequest) {
        if (!user.getEmail().equals(updateUserRequest.getEmail()) && !isEmailUnique(updateUserRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        if (!user.getUsername().equals(updateUserRequest.getUsername()) && !isUsernameUnique(updateUserRequest.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
    }

    public void deleteUser(String email){
        if(doesUserExist(email)){
            userRepository.deleteById(email);
            throw new ResponseStatusException(HttpStatus.OK, "user deleted");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user no found");
    }
    private  boolean doesUserExist(String email){
        return userRepository.existsById(email);
    }
}
