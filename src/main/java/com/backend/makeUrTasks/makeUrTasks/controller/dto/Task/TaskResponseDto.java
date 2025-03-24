package com.backend.makeUrTasks.makeUrTasks.controller.dto.Task;

import com.backend.makeUrTasks.makeUrTasks.repository.entity.Task;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Esta classe record é equivalente a uma classe com getters e constructor prontos.
 * A notação de jsonIgnoreProperties garante que qualquer propriedade passada a mais seja descartada.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record TaskResponseDto (
    @JsonProperty("title")
    String title,
    @JsonProperty("finished")
    boolean finished,
    @JsonProperty("createdAt")
    Date createdAt,
    @JsonProperty("updatedAt")
    Date updatedAt,
    @JsonProperty("id")
    Integer id
) {

  public static TaskResponseDto fromEntity(Task task) {
    return new TaskResponseDto(
        task.getTitle(),
        task.isFinished(),
        task.getCreatedAt(),
        task.getUpdatedAt(),
        task.getId()
    );
  }

  public static TaskResponseDto convert(TaskResponseDto taskResponseDto) {
    return new TaskResponseDto(
        taskResponseDto.title,
        taskResponseDto.finished,
        taskResponseDto.createdAt,
        taskResponseDto.updatedAt,
        taskResponseDto.id
    );
  }

}
