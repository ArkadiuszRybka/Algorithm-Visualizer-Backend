package com.algovis.backend.repository;

import com.algovis.backend.model.entity.QuizResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizResultRepository extends CrudRepository<QuizResult, Long> {
}
