package com.algovis.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class QuizResultDto {
    private int correctAnswers;
    private int totalQuestions;
}
