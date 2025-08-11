package com.algovis.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProgressSummaryDto {
    private int totalAlgorithms;
    private long completedCount;
    private List<ProgressItemDto> items;
}
