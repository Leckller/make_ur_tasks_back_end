package com.backend.makeUrTasks.makeUrTasks.controller;

import com.backend.makeUrTasks.makeUrTasks.dto.TarefaDto;
import com.backend.makeUrTasks.makeUrTasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller das Tarefas.
 */
@RestController
@RequestMapping("/tarefas")
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
  @GetMapping
  public String tarefas () {
    return "Olá mundo!";
  }

  /**
   * Retorna uma tarefa que tenha o "id" passado por parâmetro.
   */
  @GetMapping(path = "/{id}")
  public String pegarTarefaPeloId (@PathVariable long id) {
    return "Você pediu pela tarefa de id: %d".formatted(id);
  }

  /**
   * Retorna uma tarefa que tenha o nome correspondente ao passado por parâmetro.
   * Exemplo: <a href="http://localhost:8080/tarefas/procurar&titulo=corrida">...</a>.
   */
  @GetMapping(path = "/procurar/{titulo}")
  public String procurarTarefa (@PathVariable String titulo) {
    return "Você pediu pela tarefa de nome: %s".formatted(titulo);
  }

  @PostMapping()
  public ResponseEntity<TarefaDto> criarTarefa (@RequestBody String titulo) {
    TarefaDto tarefa = this.taskService.novaTarefa(titulo);
    return ResponseEntity.status(HttpStatus.CREATED).body(tarefa);
  }

}
