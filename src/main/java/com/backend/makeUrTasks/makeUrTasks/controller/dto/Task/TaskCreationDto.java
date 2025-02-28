package com.backend.makeUrTasks.makeUrTasks.controller.dto.Task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskCreationDto {

  public String title;

  public String description;

  public int userId;

}
