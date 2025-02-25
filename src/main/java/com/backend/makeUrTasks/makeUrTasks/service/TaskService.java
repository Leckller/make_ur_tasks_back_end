package com.backend.makeUrTasks.makeUrTasks.service;

import com.backend.makeUrTasks.makeUrTasks.abstractClasses.AbstractTask;
import com.backend.makeUrTasks.makeUrTasks.exceptions.InvalidFieldsException;
import com.backend.makeUrTasks.makeUrTasks.exceptions.TaskNotFoundException;
import com.backend.makeUrTasks.makeUrTasks.repository.TaskRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Service das Tarefas.
 */
@Service
public class TaskService {

  private final TaskRepository taskRepo;

  /**
   * Construtor da classe, aqui é injetado a model.
   */
  @Autowired
  public TaskService (TaskRepository taskRepo){
    this.taskRepo = taskRepo;
  }

  public ArrayList<AbstractTask> listTasks (Integer userId, Integer page) {
    if (userId < 0 || page < 0) {
      throw new InvalidFieldsException("userId or page must be a natural number");
    }

    return this.taskRepo.find(userId, page).orElseThrow(TaskNotFoundException::new);

  }

  public AbstractTask getTaskById (Integer taskId, Integer userId) {
    if (userId <= 0 || taskId <= 0) {
      throw new InvalidFieldsException("userId or taskId must be a Integer");
    }

    return this.taskRepo.findById(taskId, userId).orElseThrow(TaskNotFoundException::new);

  }

  public AbstractTask getTaskByTitle (String title, Integer userId) {
    if (userId <= 0) {
      throw new InvalidFieldsException("userId must be a Integer.");
    } if (title.isEmpty()) {
      throw new InvalidFieldsException("title must be a string.");
    }

    Optional<AbstractTask> task = this.taskRepo.findByTitle(title, userId);

    if(task.isEmpty()){
      throw new TaskNotFoundException();
    }

    return task.get();

  }

  public AbstractTask createTask (String title, String description, Integer userId) {

    if (title == null) {
      throw new InvalidFieldsException("title must be a string");
    } else if (description == null) {
      throw new InvalidFieldsException("description must be a string");
    } else if (userId <= 0) {
      throw new InvalidFieldsException("userId must be a Integer");
    }

    return this.taskRepo.create(title, userId, description).orElseThrow(TaskNotFoundException::new);

  }

}
