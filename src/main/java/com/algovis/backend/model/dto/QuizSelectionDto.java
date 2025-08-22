package com.algovis.backend.model.dto;

import lombok.Data;

@Data
public class QuizSelectionDto {
    private Long quizId;
    private Long selectedAnswerId;
}
