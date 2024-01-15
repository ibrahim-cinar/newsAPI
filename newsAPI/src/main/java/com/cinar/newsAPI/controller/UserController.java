package com.cinar.newsAPI.controller;

import com.cinar.newsAPI.dto.CreateUserRequest;
import com.cinar.newsAPI.dto.UserDto;
import com.cinar.newsAPI.dto.UsersNewsDto;
import com.cinar.newsAPI.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.getUserById(username));
    }
    @GetMapping("/{username}/news")
    public ResponseEntity<UsersNewsDto> getNewsByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.getNewsByUsername(username));
    }
    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
    @PostMapping
    public ResponseEntity<UserDto> CreateNewUser(@RequestBody CreateUserRequest createUserRequest){
        return ResponseEntity.ok(userService.createNewUser(createUserRequest));
    }
}
