package com.algovis.backend.repository;

import com.algovis.backend.model.entity.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends CrudRepository<Quiz, Long> {
    List<Quiz> findByAlgorithmId (Long algorithmId);
}
