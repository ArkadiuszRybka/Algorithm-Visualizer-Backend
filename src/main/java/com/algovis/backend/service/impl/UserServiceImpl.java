package com.algovis.backend.service.impl;

import com.algovis.backend.mapper.impl.UserMapper;
import com.algovis.backend.model.dto.RegisterRequest;
import com.algovis.backend.model.dto.UserDto;
import com.algovis.backend.model.entity.User;
import com.algovis.backend.repository.UserRepository;
import com.algovis.backend.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto register(RegisterRequest request) {
        // Sprawdzenie czy istnieje email
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User with this email already exists");
        }

        // Tworzenie nowego usera
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);

        return userMapper.mapTo(savedUser);
    }
}
