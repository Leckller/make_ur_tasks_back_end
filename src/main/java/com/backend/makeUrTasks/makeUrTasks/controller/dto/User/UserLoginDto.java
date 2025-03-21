package com.backend.makeUrTasks.makeUrTasks.controller.dto.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginDto(
    @NotBlank(message="Name is required")
    String email,

    @Size(min=8, message="Password must have at least 8 characters")
    @NotBlank(message="Password is required")
    String password
) { }
