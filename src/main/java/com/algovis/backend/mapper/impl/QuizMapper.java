package com.algovis.backend.mapper.impl;

import com.algovis.backend.mapper.Mapper;
import com.algovis.backend.model.dto.QuizAnswerDto;
import com.algovis.backend.model.dto.QuizQuestionDto;
import com.algovis.backend.model.entity.Quiz;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuizMapper implements Mapper<Quiz, QuizQuestionDto> {
    private ModelMapper modelMapper;

    public QuizMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public QuizQuestionDto mapTo(Quiz quiz) {
        QuizQuestionDto dto = new QuizQuestionDto();
        dto.setId(quiz.getId());
        dto.setQuestion(quiz.getQuestion());
        List<QuizAnswerDto> answers = quiz.getAnswers().stream()
                .map(a -> new QuizAnswerDto(a.getId(), a.getText()))
                .collect(Collectors.toList());
        dto.setAnswers(answers);
        return dto;
    }

    @Override
    public Quiz mapFrom(QuizQuestionDto quizQuestionDto) {
        throw new UnsupportedOperationException("Mapping from DTO not supported for Quiz");
    }
}
