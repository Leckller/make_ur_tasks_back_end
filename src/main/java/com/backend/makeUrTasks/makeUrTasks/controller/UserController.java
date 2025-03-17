package com.backend.makeUrTasks.makeUrTasks.controller;

import com.backend.makeUrTasks.makeUrTasks.controller.dto.Task.TaskResponseDto;
import com.backend.makeUrTasks.makeUrTasks.controller.dto.User.UserCreationDto;
import com.backend.makeUrTasks.makeUrTasks.controller.dto.User.UserLoginDto;
import com.backend.makeUrTasks.makeUrTasks.controller.dto.User.UserResponseDto;
import com.backend.makeUrTasks.makeUrTasks.repository.entity.Task;
import com.backend.makeUrTasks.makeUrTasks.repository.entity.User;
import com.backend.makeUrTasks.makeUrTasks.service.TaskService;
import com.backend.makeUrTasks.makeUrTasks.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @PostMapping("/register")
  public ResponseEntity<String> register(@Valid @RequestBody UserCreationDto userCreationDto) {
    User user = this.userService.createUser(userCreationDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(UserResponseDto.token(user));
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@Valid @RequestBody UserCreationDto userCreationDto) {

    return null;
  }

  @GetMapping
  public ResponseEntity<List<TaskResponseDto>> userTasks(@Valid @RequestBody UserLoginDto userLoginDto) {

    return null;
  }

}
