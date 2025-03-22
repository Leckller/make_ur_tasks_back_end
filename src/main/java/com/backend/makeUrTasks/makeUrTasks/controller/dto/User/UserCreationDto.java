package com.backend.makeUrTasks.makeUrTasks.controller.dto.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserCreationDto(
    @NotBlank(message="O Nome é obrigatório")
    @JsonProperty("name")
    String name,

    @NotBlank(message="O Username é obrigatório")
    @JsonProperty("username")
    String username,

    @Email(message="Formato de email inválido")
    @NotBlank(message="O Email é obrigatório")
    @JsonProperty("email")
    String email,

    @Size(min=8, message="A senha deve ter pelo menos 8 caracteres")
    @NotBlank(message="A senha é obrigatória")
    @JsonProperty("password")
    String password
) { }
