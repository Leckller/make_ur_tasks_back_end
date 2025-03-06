package com.backend.makeUrTasks.makeUrTasks.controller;

import com.backend.makeUrTasks.makeUrTasks.controller.dto.Task.TaskCreationDto;
import com.backend.makeUrTasks.makeUrTasks.controller.dto.Task.TaskGetByIdDto;
import com.backend.makeUrTasks.makeUrTasks.controller.dto.Task.TaskResponseDto;
import com.backend.makeUrTasks.makeUrTasks.repository.entity.Task;
import com.backend.makeUrTasks.makeUrTasks.service.TaskService;
import com.backend.makeUrTasks.makeUrTasks.service.exceptions.NoPermissionException;
import com.backend.makeUrTasks.makeUrTasks.service.exceptions.TaskNotFoundException;
import com.backend.makeUrTasks.makeUrTasks.service.exceptions.UserNotFoundException;
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
   * Construtor da Classe, aqui é instanciado por injeção de dependência a service das tarefas.
   */
  @Autowired
  public TaskController (TaskService taskService){
    this.taskService = taskService;
  }

  /**
   * Retorna uma tarefa que tenha o "id" passado por parâmetro.
   */
  @GetMapping("/{taskId}/user/{userId}")
  public ResponseEntity<TaskResponseDto> getTaskById (@PathVariable Integer taskId, @PathVariable Integer userId)
      throws UserNotFoundException, TaskNotFoundException, NoPermissionException {

    Task task = this.taskService.findTaskById(userId, taskId);

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(TaskResponseDto.fromEntity(task));

  }

  @GetMapping("/list/{userId}")
  public ResponseEntity<List<TaskResponseDto>> getTasks (@PathVariable Integer userId)
      throws UserNotFoundException {

    List<Task> tasks = this.taskService.listTasks(userId);

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(tasks.stream().map(TaskResponseDto::fromEntity).toList());
  }

  @PostMapping()
  public ResponseEntity<TaskResponseDto> createTask (@RequestBody TaskCreationDto taskCreationDto)
      throws UserNotFoundException {

    Task task = this.taskService.createTask(taskCreationDto);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(TaskResponseDto.fromEntity(task));

  }

}
