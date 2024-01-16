package com.cinar.newsAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNewsRequest {
    private String description;
    private String title;
    private LocalDateTime updatedTime;
    private String photoPath;
}
