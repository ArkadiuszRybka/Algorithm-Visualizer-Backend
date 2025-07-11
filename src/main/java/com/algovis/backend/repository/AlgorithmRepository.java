package com.algovis.backend.repository;

import com.algovis.backend.model.entity.Algorithm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlgorithmRepository extends CrudRepository<Algorithm, Long> {
}
