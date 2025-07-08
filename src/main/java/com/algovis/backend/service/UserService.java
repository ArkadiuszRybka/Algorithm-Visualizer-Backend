package com.algovis.backend.service;

import com.algovis.backend.model.dto.RegisterRequest;
import com.algovis.backend.model.dto.UserDto;
import com.algovis.backend.model.entity.User;

import java.util.Optional;


public interface UserService {
    UserDto register(RegisterRequest request);
}
