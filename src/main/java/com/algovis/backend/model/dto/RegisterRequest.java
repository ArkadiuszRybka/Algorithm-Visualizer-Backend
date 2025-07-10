package com.algovis.backend.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email is not valid")
    private String email;
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password needs at least 8 characters")
    private String password;
}
