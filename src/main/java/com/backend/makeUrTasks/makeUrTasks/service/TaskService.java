package com.backend.makeUrTasks.makeUrTasks.service;

import com.backend.makeUrTasks.makeUrTasks.controller.dto.Task.TaskCreationDto;
import com.backend.makeUrTasks.makeUrTasks.repository.TaskRepository;
import com.backend.makeUrTasks.makeUrTasks.repository.UserRepository;
import com.backend.makeUrTasks.makeUrTasks.repository.entity.Task;
import com.backend.makeUrTasks.makeUrTasks.repository.entity.User;
import com.backend.makeUrTasks.makeUrTasks.service.exceptions.UserNotFoundException;
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

  public Task createTask(TaskCreationDto taskCreationDto, String username)
      throws UserNotFoundException {

    User user = this.userService
        .findUserByUsername(username);

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
