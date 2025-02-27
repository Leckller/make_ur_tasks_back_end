package com.backend.makeUrTasks.makeUrTasks.controller.dto;

import com.backend.makeUrTasks.makeUrTasks.repository.entity.Task;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Esta classe record é equivalente a uma classe com getters e constructor prontos.
 * A notação de jsonIgnoreProperties garante que qualquer propriedade passada a mais seja descartada.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskResponseDto {
  public String title;
  public boolean finished;
  public Date createdAt;
  public Date updatedAt;
  public String description;
  public Integer id;

  public TaskResponseDto(
    String title,
    boolean finished,
    Date createdAt,
    Date updatedAt,
    String description,
    Integer id
  ) {
    this.id = id;
    this.title = title;
    this.finished = finished;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.description = description;
  }

  public TaskResponseDto(
    Task task
  ) {
    this.id = task.getId();
    this.title = task.getTitle();
    this.finished = task.isFinished();
    this.createdAt = task.getCreatedAt();
    this.updatedAt = task.getUpdatedAt();
    this.description = task.getDescription();
  }

}
