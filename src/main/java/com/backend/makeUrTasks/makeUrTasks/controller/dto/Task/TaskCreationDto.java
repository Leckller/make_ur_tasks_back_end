package com.backend.makeUrTasks.makeUrTasks.controller.dto.Task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TaskCreationDto (
    @NotBlank(message="Title is required")
    @Size(min=3, message="Title must have at least 8 characters")
    @JsonProperty("title")
    String title
) { }
