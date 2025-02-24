package com.backend.makeUrTasks.makeUrTasks.controller;

import com.backend.makeUrTasks.makeUrTasks.AbstractClasses.AbstractTask;
import com.backend.makeUrTasks.makeUrTasks.dto.TaskRequestDto;
import com.backend.makeUrTasks.makeUrTasks.dto.TaskResponseDto;
import com.backend.makeUrTasks.makeUrTasks.service.TaskService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    List<AbstractTask> tasks = this.taskService.listTasks(1, page);

    List<TaskResponseDto> tasksDto = tasks.stream().map(TaskResponseDto::new).toList();

    return ResponseEntity.status(HttpStatus.OK).body(tasksDto);

  }

  /**
   * Retorna uma tarefa que tenha o "id" passado por parâmetro.
   */
  @GetMapping(path = "/id/{id}")
  public String pegarTarefaPeloId (@PathVariable long id) {
    return "Você pediu pela tarefa de id: %d".formatted(id);
  }

  /**
   * Retorna uma tarefa que tenha o nome correspondente ao passado por parâmetro.
   * Exemplo: <a href="http://localhost:8080/tarefas/procurar&titulo=corrida">...</a>.
   */
  @GetMapping(path = "/title/{title}")
  public String procurarTarefa (@PathVariable String title) {
    return "Você pediu pela tarefa de nome: %s".formatted(title);
  }

  @PostMapping()
  public ResponseEntity<TaskResponseDto> createTask (@RequestBody TaskRequestDto request) throws BadRequestException {

    AbstractTask task = this.taskService.createTask(request.title, request.description, request.userId);
    TaskResponseDto taskDto = new TaskResponseDto(task);

    return ResponseEntity.status(HttpStatus.CREATED).body(taskDto);

  }

}
