package com.backend.makeUrTasks.makeUrTasks.service;

import com.backend.makeUrTasks.makeUrTasks.controller.dto.Task.TaskCreationDto;
import com.backend.makeUrTasks.makeUrTasks.repository.TaskRepository;
import com.backend.makeUrTasks.makeUrTasks.repository.UserRepository;
import com.backend.makeUrTasks.makeUrTasks.repository.entity.Task;
import com.backend.makeUrTasks.makeUrTasks.repository.entity.User;
import com.backend.makeUrTasks.makeUrTasks.service.exceptions.NoPermissionException;
import com.backend.makeUrTasks.makeUrTasks.service.exceptions.TaskNotFoundException;
import com.backend.makeUrTasks.makeUrTasks.service.exceptions.UserNotFoundException;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Service das Tarefas.
 */
@Service
public class TaskService {

  private final TaskRepository taskRepository;
  private final UserRepository userRepository;
  private final UserService userService;

  @Autowired
  public TaskService(TaskRepository taskRepository, UserRepository userRepository, UserService userService) {
    this.taskRepository = taskRepository;
    this.userRepository = userRepository;
    this.userService = userService;
  }

  public Task findTaskById(Integer userId, Integer taskId)
      throws UserNotFoundException, TaskNotFoundException, NoPermissionException {

    User user = this.userService.findUserById(userId);

    Task task = this.taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);

    if(!Objects.equals(task.getUser().getId(), userId)) {
      throw new NoPermissionException();
    }

    return task;

  }

  public Task createTask(TaskCreationDto taskCreationDto)
      throws UserNotFoundException {

    User user = this.userService.findUserById(taskCreationDto.userId());

    Task task = new Task(taskCreationDto, user);

    return this.taskRepository.save(task);

  }

  public List<Task> listTasks(Integer userId)
      throws UserNotFoundException {

    User user = this.userService.findUserById(userId);

    return user.getTasks();

  }

}
