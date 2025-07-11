package com.algovis.backend.mapper.impl;

import com.algovis.backend.mapper.Mapper;
import com.algovis.backend.model.dto.AlgorithmDto;
import com.algovis.backend.model.entity.Algorithm;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AlgorithmMapper implements Mapper<Algorithm, AlgorithmDto> {
    private ModelMapper modelMapper;

    public AlgorithmMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AlgorithmDto mapTo(Algorithm algorithm) {
        return modelMapper.map(algorithm, AlgorithmDto.class);
    }

    @Override
    public Algorithm mapFrom(AlgorithmDto algorithmDto) {
        return modelMapper.map(algorithmDto, Algorithm.class);
    }
}
