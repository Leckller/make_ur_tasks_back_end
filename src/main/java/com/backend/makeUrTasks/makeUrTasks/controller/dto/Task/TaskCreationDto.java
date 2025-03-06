package com.backend.makeUrTasks.makeUrTasks.controller.dto.Task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TaskCreationDto (
    String title,
    String description,
    Integer userId
) {

  public static TaskCreationDto makeDto (String title, String description, Integer userId) {
    return new TaskCreationDto(title, description, userId);
  }

}
