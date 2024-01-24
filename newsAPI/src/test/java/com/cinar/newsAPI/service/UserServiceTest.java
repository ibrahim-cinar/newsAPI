package com.cinar.newsAPI.service;

import com.cinar.newsAPI.dto.converter.UserDtoConverter;
import com.cinar.newsAPI.model.User;
import com.cinar.newsAPI.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserService userService;
    private  UserRepository userRepository;
    private  UserDtoConverter userDtoConverter;
    private  BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceTest(UserService userService, UserRepository userRepository, UserDtoConverter userDtoConverter, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @BeforeEach()
    public void setUp(){
        userRepository = mock(UserRepository.class);
        userDtoConverter = mock(UserDtoConverter.class);
        bCryptPasswordEncoder  = mock(BCryptPasswordEncoder.class);
        userService = new UserService(userRepository, userDtoConverter, bCryptPasswordEncoder);
    }

    /*@Test
    public void testGetAllUser(){
        userService.getAllUser();
    }*/

@Test
public void testFindUserById_whenUserIdExist_shouldReturnUser(){
        User user = new User("id","sa", "sa", "sa", "sa", "sa@gmail.com",
                true, true, true, true,
                List.of(), List.of(), List.of()
        );
        Mockito.when(userRepository.findById("id")).thenReturn(Optional.of(user));
        User result = userService.findUserById("id");
        assertEquals(user, result);

    }
}