package com.backend.makeUrTasks.makeUrTasks.controller;

import com.backend.makeUrTasks.makeUrTasks.controller.dto.TaskRequestDto;
import com.backend.makeUrTasks.makeUrTasks.controller.dto.TaskResponseDto;
import com.backend.makeUrTasks.makeUrTasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller das Tarefas.
 */
@RestController
@RequestMapping("/task")
public class TaskController {

  private final TaskService taskService;

  /**
   * Construtor da Classe, aqui é instanciado por injeção de dependência a service das tarefas.s
   */
  @Autowired
  public TaskController (TaskService taskService){
    this.taskService = taskService;
  }

  /**
   * Retorna as tarefas.
   */
  @GetMapping(path = "/list/{page}")
  public ResponseEntity<List<TaskResponseDto>> listTasks (@PathVariable Integer page) {

    return null;

  }

  /**
   * Retorna uma tarefa que tenha o "id" passado por parâmetro.
   */
  @GetMapping(path = "/id/{id}")
  public ResponseEntity<TaskResponseDto> getTaskById (@PathVariable Integer id) {

    return null;

  }

  /**
   * Retorna uma tarefa que tenha o nome correspondente ao passado por parâmetro.
   */
  @GetMapping(path = "/title/{title}")
  public ResponseEntity<TaskResponseDto> getTaskByTitle (@PathVariable String title) {

    return null;

  }

  @PostMapping()
  public ResponseEntity<TaskResponseDto> createTask (@RequestBody TaskRequestDto request) {

    return null;

  }

}
