package com.backend.makeUrTasks.makeUrTasks.controller.dto.User;

import com.backend.makeUrTasks.makeUrTasks.repository.entity.User;

public record UserResponseDto (String token) {

  public static String token (User user) {
    return new UserResponseDto(user.getId().toString()).token;
  }

}
