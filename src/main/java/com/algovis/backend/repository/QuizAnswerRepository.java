package com.algovis.backend.repository;

import com.algovis.backend.model.entity.QuizAnswer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizAnswerRepository extends CrudRepository<QuizAnswer, Long> {
}
