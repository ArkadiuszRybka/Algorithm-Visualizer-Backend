package com.algovis.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlgorithmDto {
    private Long id;
    private String name;
    private String category;
    private String description;
}
