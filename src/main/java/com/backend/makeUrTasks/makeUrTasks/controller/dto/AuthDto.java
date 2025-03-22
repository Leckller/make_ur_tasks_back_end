package com.backend.makeUrTasks.makeUrTasks.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthDto(
    @JsonProperty("username")
    String username,
    @JsonProperty("password")
    String password
) { }
