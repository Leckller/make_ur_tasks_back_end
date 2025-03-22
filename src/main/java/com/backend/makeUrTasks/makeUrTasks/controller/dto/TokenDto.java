package com.backend.makeUrTasks.makeUrTasks.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TokenDto(
    @JsonProperty("token")
    String token
) {
}
