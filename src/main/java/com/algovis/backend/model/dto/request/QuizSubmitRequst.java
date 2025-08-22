package com.algovis.backend.model.dto.request;

import com.algovis.backend.model.dto.QuizSelectionDto;
import lombok.Data;

import java.util.List;

@Data
public class QuizSubmitRequst {
    private List<QuizSelectionDto> answers;
}
