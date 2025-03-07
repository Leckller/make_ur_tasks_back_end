package com.backend.makeUrTasks.makeUrTasks.controller;

import com.backend.makeUrTasks.makeUrTasks.controller.dto.Task.TaskResponseDto;
import com.backend.makeUrTasks.makeUrTasks.controller.dto.User.UserCreationDto;
import com.backend.makeUrTasks.makeUrTasks.controller.dto.User.UserLoginDto;
import com.backend.makeUrTasks.makeUrTasks.repository.entity.Task;
import com.backend.makeUrTasks.makeUrTasks.service.TaskService;
import com.backend.makeUrTasks.makeUrTasks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;
  private final TaskService taskService;

  @Autowired
  public UserController(UserService userService, TaskService taskService) {
    this.userService = userService;
    this.taskService = taskService;
  }

  public ResponseEntity<String> register(UserCreationDto userCreationDto) {
    return null;
  }

  public ResponseEntity<String> login(UserCreationDto userCreationDto) {
    return null;
  }

  public ResponseEntity<List<TaskResponseDto>> userTasks(UserLoginDto userLoginDto) {
    return null;
  }

}
