package com.algovis.backend.service;

import com.algovis.backend.model.entity.Algorithm;

import java.util.List;
import java.util.Optional;

public interface AlgorithmService {
    List<Algorithm> findAll();
    Optional<Algorithm> findOneAlgorithm(Long id);
}
