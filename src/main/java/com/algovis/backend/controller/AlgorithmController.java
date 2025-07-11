package com.algovis.backend.controller;

import com.algovis.backend.mapper.impl.AlgorithmMapper;
import com.algovis.backend.model.dto.AlgorithmDto;
import com.algovis.backend.model.entity.Algorithm;
import com.algovis.backend.service.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
public class AlgorithmController {
    private AlgorithmService algorithmService;
    private AlgorithmMapper algorithmMapper;

    public AlgorithmController(AlgorithmService algorithmService, AlgorithmMapper algorithmMapper) {
        this.algorithmService = algorithmService;
        this.algorithmMapper = algorithmMapper;
    }

    @GetMapping(path = "/algorithms")
    public List<AlgorithmDto> listAlgorithms() {
        List<Algorithm> algorithms = algorithmService.findAll();
        return algorithms.stream()
                .map(algorithmMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/algorithms/{id}")
    public ResponseEntity<AlgorithmDto> findAlgorithm(@PathVariable("id") Long id) {
        Optional<Algorithm> foundAlgo = algorithmService.findOneAlgorithm(id);
        return foundAlgo.map(algorithm -> {
            AlgorithmDto algorithmDto = algorithmMapper.mapTo(algorithm);
            return new ResponseEntity<>(algorithmDto, HttpStatus.OK);
        }).orElseThrow(() -> new RuntimeException("Algorithm not found"));
    }
}
