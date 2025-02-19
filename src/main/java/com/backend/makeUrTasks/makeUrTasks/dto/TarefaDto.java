package com.backend.makeUrTasks.makeUrTasks.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Esta classe record é equivalente a uma classe com getters e constructor prontos.
 * A notação de jsonIgnoreProperties garante que qualquer propriedade passada a mais seja descartada.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TarefaDto {
  
  public TarefaDto(
    String titulo,
    boolean finished,
    Date createdAt,
    Date updatedAt,
    String description
  ) {}

}
