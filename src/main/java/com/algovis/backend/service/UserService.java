package com.algovis.backend.service;

import com.algovis.backend.model.dto.request.ChangePasswordRequest;
import com.algovis.backend.model.dto.request.RegisterRequest;
import com.algovis.backend.model.dto.UserDto;


public interface UserService {
    UserDto register(RegisterRequest request);
    void deleteUserByEmail(String email);
    void changePasswordByEmail(String email, ChangePasswordRequest request);
    UserDto getProfile(String email);
}
