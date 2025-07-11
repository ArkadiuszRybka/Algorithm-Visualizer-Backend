package com.algovis.backend.service.impl;

import com.algovis.backend.model.entity.Algorithm;
import com.algovis.backend.repository.AlgorithmRepository;
import com.algovis.backend.service.AlgorithmService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AlgorithmServiceImpl implements AlgorithmService {
    private AlgorithmRepository algorithmRepository;

    public AlgorithmServiceImpl(AlgorithmRepository algorithmRepository) {
        this.algorithmRepository = algorithmRepository;
    }

    @Override
    public List<Algorithm> findAll() {
        return StreamSupport.stream(algorithmRepository
                .findAll()
                .spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<Algorithm> findOneAlgorithm(Long id) {
        return algorithmRepository.findById(id);
    }
}
