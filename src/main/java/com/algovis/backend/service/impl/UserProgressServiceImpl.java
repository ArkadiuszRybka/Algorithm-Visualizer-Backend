package com.algovis.backend.service.impl;

import com.algovis.backend.model.dto.ProgressItemDto;
import com.algovis.backend.model.dto.ProgressSummaryDto;
import com.algovis.backend.model.entity.Algorithm;
import com.algovis.backend.model.entity.User;
import com.algovis.backend.model.entity.UserProgress;
import com.algovis.backend.repository.UserProgressRepository;
import com.algovis.backend.repository.UserRepository;
import com.algovis.backend.service.AlgorithmService;
import com.algovis.backend.service.UserProgressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserProgressServiceImpl implements UserProgressService {
    private final AlgorithmService algorithmService;
    private final UserProgressRepository userProgressRepository;
    private final UserRepository userRepository;

    public UserProgressServiceImpl(AlgorithmService algorithmService, UserProgressRepository userProgressRepository, UserRepository userRepository) {
        this.algorithmService = algorithmService;
        this.userProgressRepository = userProgressRepository;
        this.userRepository = userRepository;
    }

    public ProgressSummaryDto getSummaryForUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        List<Algorithm> algorithms = StreamSupport.stream(
                algorithmService.findAll().spliterator(), false
        ).toList();

        List<UserProgress> progressList = userProgressRepository.findAllByUserId(user.getId());

        Map<Long, String> statusByAlgo = progressList.stream()
                .collect(Collectors.toMap(
                        up -> up.getAlgorithm().getId(),
                        UserProgress::getProgressStatus,
                        (a,b) -> a
                ));

        List<ProgressItemDto> items = algorithms.stream()
                .map(a -> new ProgressItemDto(
                        a.getId(),
                        a.getName(),
                        statusByAlgo.getOrDefault(a.getId(), "NOT_STARTED")
                )).toList();

        long completed = items.stream()
                .filter(i -> i.getStatus().equals("COMPLETED"))
                .count();

        return new ProgressSummaryDto(algorithms.size(), completed, items);
    }
}
