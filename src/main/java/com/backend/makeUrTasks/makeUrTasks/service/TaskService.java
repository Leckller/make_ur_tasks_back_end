package com.backend.makeUrTasks.makeUrTasks.service;

import com.backend.makeUrTasks.makeUrTasks.AbstractClasses.AbstractTask;
import com.backend.makeUrTasks.makeUrTasks.Exceptions.InvalidFieldsException;
import com.backend.makeUrTasks.makeUrTasks.model.TaskModel;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

  public List<AbstractTask> listTasks (Integer userId, Integer page) {
    if (userId < 0) {
      throw new InvalidFieldsException("userId must be a Integer");
    }

    return this.taskModel.find(userId, page);

  }

  public AbstractTask getTaskById (Integer taskId, Integer userId) {
    if (userId <= 0 || taskId <= 0) {
      throw new InvalidFieldsException("userId or taskId must be a Integer");
    }

    return this.taskModel.findById(taskId, userId);

  }

  public AbstractTask createTask (String title, String description, Integer userId) throws BadRequestException {

    if (title == null) {
      throw new InvalidFieldsException("title must be a string");
    } else if (description == null) {
      throw new InvalidFieldsException("description must be a string");
    } else if (userId <= 0) {
      throw new InvalidFieldsException("userId must be a Integer");
    }

    return this.taskModel.create(title, userId, description);

  }

}
