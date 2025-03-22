package com.backend.makeUrTasks.makeUrTasks.controller.dto.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserLoginDto(
    @NotBlank(message="Name is required")
    @JsonProperty("email")
    String email,

    @Size(min=8, message="Password must have at least 8 characters")
    @NotBlank(message="Password is required")
    @JsonProperty("password")
    String password
) { }
