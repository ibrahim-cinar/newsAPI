package com.cinar.newsAPI.controller;

import com.cinar.newsAPI.dto.*;
import com.cinar.newsAPI.model.User;
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
    @GetMapping("/password")
    public ResponseEntity<User> getDecodePassword(@RequestBody UsersPassword usersPassword){
        return ResponseEntity.ok(userService.decodePassword(usersPassword));

    }
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
    @PostMapping
    public ResponseEntity<UserDto> CreateNewUser(@RequestBody CreateUserRequest createUserRequest){
        return ResponseEntity.ok(userService.createNewUser(createUserRequest));
    }

    @PutMapping("/{email}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UpdateUserRequest updateUserRequest,@PathVariable String email){
        return ResponseEntity.ok(userService.updateUser(email,updateUserRequest));
    }
    @DeleteMapping("/{username}")
    public  ResponseEntity<UserDto> deleteUser(@PathVariable String username){
        userService.deleteUser(username);
        return ResponseEntity.ok().build();
    }
}
