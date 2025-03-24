package com.backend.makeUrTasks.makeUrTasks.controller;

import com.backend.makeUrTasks.makeUrTasks.controller.dto.Task.TaskCreationDto;
import com.backend.makeUrTasks.makeUrTasks.controller.dto.Task.TaskResponseDto;
import com.backend.makeUrTasks.makeUrTasks.repository.entity.Task;
import com.backend.makeUrTasks.makeUrTasks.service.TaskService;
import com.backend.makeUrTasks.makeUrTasks.service.exceptions.NoPermissionException;
import com.backend.makeUrTasks.makeUrTasks.service.exceptions.NotFound.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller das Tarefas.
 */
@RestController
@RequestMapping("/task")
@CrossOrigin()
public class TaskController {

  private final TaskService taskService;

  /**
   * Construtor da Classe, aqui é instanciado por injeção de dependência a service das tarefas.
   */
  @Autowired
  public TaskController (TaskService taskService){
    this.taskService = taskService;
  }

  @GetMapping
  public ResponseEntity<List<TaskResponseDto>> getTasks (
      @RequestParam(required = false, defaultValue = "0") Integer page
  ) throws UserNotFoundException {

    String username = SecurityContextHolder.getContext().getAuthentication().getName();

    List<Task> tasks = this.taskService.listTasks(page, username);

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(tasks.stream().map(TaskResponseDto::fromEntity).toList());
  }

  @PostMapping()
  public ResponseEntity<TaskResponseDto> createTask (@RequestBody TaskCreationDto taskCreationDto)
      throws UserNotFoundException {

    String username = SecurityContextHolder.getContext().getAuthentication().getName();

    Task task = this.taskService.createTask(taskCreationDto, username);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(TaskResponseDto.fromEntity(task));

  }

  @PatchMapping("/{id}")
  public ResponseEntity<TaskResponseDto> toggleTask (
      @PathVariable Integer id
  ) throws UserNotFoundException, NoPermissionException {

    String username = SecurityContextHolder.getContext().getAuthentication().getName();

    Task task = this.taskService.toggleTask(id, username);

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(TaskResponseDto.fromEntity(task));

  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteTask (
      @PathVariable Integer id
  ) throws UserNotFoundException, NoPermissionException {

    String username = SecurityContextHolder.getContext().getAuthentication().getName();

    this.taskService.deleteTask(id, username);

    return ResponseEntity
        .status(HttpStatus.OK).build();

  }

}
