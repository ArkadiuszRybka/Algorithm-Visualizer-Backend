package com.algovis.backend.service;

import com.algovis.backend.model.dto.QuizResultDto;
import com.algovis.backend.model.dto.request.QuizSubmitRequst;
import com.algovis.backend.model.entity.Quiz;

import java.util.List;

public interface QuizService {
    List<Quiz> findByAlgorithmIdShuffled(Long algorithmId);

    QuizResultDto evaluateAndSaveResult(Long algorithmId, String userEmail, QuizSubmitRequst requst);
}
