package com.algovis.backend.service;

import com.algovis.backend.model.entity.Quiz;

import java.util.List;

public interface QuizService {
    List<Quiz> findByAlgorithmIdShuffled(Long algorithmId);
}
