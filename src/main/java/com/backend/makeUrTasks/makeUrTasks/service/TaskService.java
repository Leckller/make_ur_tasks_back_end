package com.backend.makeUrTasks.makeUrTasks.service;

import com.backend.makeUrTasks.makeUrTasks.controller.dto.Task.TaskCreationDto;
import com.backend.makeUrTasks.makeUrTasks.repository.TaskRepository;
import com.backend.makeUrTasks.makeUrTasks.repository.UserRepository;
import com.backend.makeUrTasks.makeUrTasks.repository.entity.Task;
import com.backend.makeUrTasks.makeUrTasks.repository.entity.User;
import com.backend.makeUrTasks.makeUrTasks.service.exceptions.NoPermissionException;
import com.backend.makeUrTasks.makeUrTasks.service.exceptions.NotFound.TaskNotFoundException;
import com.backend.makeUrTasks.makeUrTasks.service.exceptions.NotFound.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;


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

  public Task toggleTask(Integer id, String username)
      throws NoPermissionException, TaskNotFoundException, UserNotFoundException {

    User user = this.userService
        .findByUsername(username);

    Task task = this.taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);

    if (task.getUser() != user) {
      throw new NoPermissionException();
    }

    task.toggleFinished();

    this.taskRepository.save(task);

    return task;

  }

  public void deleteTask(Integer id, String username)
      throws NoPermissionException, TaskNotFoundException, UserNotFoundException {

    User user = this.userService
        .findByUsername(username);

    Task task = this.taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);

    if (task.getUser() != user) {
      throw new NoPermissionException();
    }

    this.taskRepository.delete(task);

  }

  public Task createTask(TaskCreationDto taskCreationDto, String username)
      throws UserNotFoundException {

    User user = this.userService
        .findByUsername(username);

    Task task = new Task(taskCreationDto, user);

    return this.taskRepository.save(task);

  }

  public List<Task> listTasks(Integer page, String username)
      throws UserNotFoundException {

    Pageable pageable = PageRequest.of(page, 15);

    User user = (User) this.userService.loadUserByUsername(username);

    Page<Task> tasksPage = this.taskRepository.findAllByUser(user, pageable);

    return tasksPage.getContent();

  }

}
