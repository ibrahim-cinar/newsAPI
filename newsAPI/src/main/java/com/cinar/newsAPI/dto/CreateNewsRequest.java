package com.cinar.newsAPI.dto;

import com.cinar.newsAPI.model.News;
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
public class CreateNewsRequest {
    private String description;
    private String title;
    private LocalDateTime createdTime;
    private String photoPath;
    private List<CommentDto> commentsDto;
}
