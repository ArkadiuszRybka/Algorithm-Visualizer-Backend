package com.algovis.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProgressItemDto {
    private Long algorithmId;
    private String algorithmName;
    private String status;
}
