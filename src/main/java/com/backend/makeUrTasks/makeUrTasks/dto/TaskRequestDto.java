package com.backend.makeUrTasks.makeUrTasks.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskRequestDto {

  public String title;

  public String description;

  public int userId;

}
