package com.cinar.newsAPI.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserRequest {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

}
