package com.cinar.newsAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsUserDto {
    private String description;
    private String title;
    private LocalDateTime createdTime;
    private String photoPath;
    private List<CommentDto> commentsDto;
    private UserDto userDto;
}
