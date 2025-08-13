package com.algovis.backend.controller;

import com.algovis.backend.mapper.impl.QuizMapper;
import com.algovis.backend.model.dto.QuizAnswerDto;
import com.algovis.backend.model.dto.QuizQuestionDto;
import com.algovis.backend.model.entity.Quiz;
import com.algovis.backend.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class QuizController {
    private final QuizService quizService;
    private final QuizMapper quizMapper;

    public QuizController(QuizService quizService, QuizMapper quizMapper) {
        this.quizService = quizService;
        this.quizMapper = quizMapper;
    }

    @GetMapping("/quiz/{algorithmId}")
    public ResponseEntity<List<QuizQuestionDto>> getQuiz(@PathVariable Long algorithmId) {
        List<Quiz> quizzes = quizService.findByAlgorithmIdShuffled(algorithmId);
        List<QuizQuestionDto> dto = quizzes.stream()
                .map(quizMapper::mapTo)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dto);
    }
}
