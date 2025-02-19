package com.backend.makeUrTasks.makeUrTasks.service;

import com.backend.makeUrTasks.makeUrTasks.dto.TarefaDto;
import com.backend.makeUrTasks.makeUrTasks.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.apache.coyote.http11.Constants.a;

/**
 * Service das Tarefas.
 */
@Service
public class TaskService {

  private final TaskModel taskModel;

  /**
   * Construtor da classe, aqui é injetado a model.
   */
  @Autowired
  public TaskService (TaskModel taskModel){
    this.taskModel = taskModel;
  }

  public ResponseEntity<String> novaTarefa (String titulo) {

    try {



      return ResponseEntity.status(HttpStatus.OK).body();

    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro");
    }

  }

}
