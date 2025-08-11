package com.algovis.backend.controller;

import com.algovis.backend.model.dto.ProgressSummaryDto;
import com.algovis.backend.service.UserProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProgressController {
    private final UserProgressService userProgressService;

    public UserProgressController(UserProgressService userProgressService) {
        this.userProgressService = userProgressService;
    }

    @GetMapping("/summary")
    public ResponseEntity<ProgressSummaryDto> getSummary(Authentication authentication){
        String email = authentication.getName();
        ProgressSummaryDto dto = userProgressService.getSummaryForUser(email);
        return ResponseEntity.ok(dto);
    }
}
