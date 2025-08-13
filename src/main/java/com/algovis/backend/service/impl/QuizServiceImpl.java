package com.algovis.backend.service.impl;

import com.algovis.backend.model.dto.QuizAnswerDto;
import com.algovis.backend.model.entity.Quiz;
import com.algovis.backend.model.entity.QuizAnswer;
import com.algovis.backend.repository.QuizRepository;
import com.algovis.backend.service.QuizService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;

    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public List<Quiz> findByAlgorithmIdShuffled(Long algorithmId) {
        List<Quiz> quizzes = new ArrayList<>(quizRepository.findByAlgorithmId(algorithmId));
        Collections.shuffle(quizzes);
        for(Quiz q : quizzes){
            List<QuizAnswer> answers = new ArrayList<>(q.getAnswers());
            Collections.shuffle(answers);
            q.getAnswers().clear();
            q.getAnswers().addAll(answers);
        }
        return quizzes;
    }
}
