package com.algovis.backend.service;

import com.algovis.backend.model.dto.ProgressSummaryDto;

public interface UserProgressService {
    ProgressSummaryDto getSummaryForUser(String email);
}
