package com.algovis.backend.controller;

import com.algovis.backend.model.dto.ChangePasswordRequest;
import com.algovis.backend.model.dto.UserDto;
import com.algovis.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @DeleteMapping("/user")
    public ResponseEntity deleteUser(Authentication authentication) {
        String email = authentication.getName();
        userService.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/user")
    public ResponseEntity<?> changePassword(Authentication authentication, @RequestBody ChangePasswordRequest request){
        String email = authentication.getName();
        userService.changePasswordByEmail(email, request);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/user")
    public ResponseEntity<UserDto> getProfile(Authentication authentication){
        String email = authentication.getName();
        UserDto userDto = userService.getProfile(email);
        return ResponseEntity.ok(userDto);
    }
}
