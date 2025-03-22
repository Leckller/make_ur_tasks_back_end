package com.backend.makeUrTasks.makeUrTasks.service.exceptions.NotFound;

public class UserNotFoundException extends NotFoundException {
  public UserNotFoundException () {
    super("Usuário não encontrado!");
  }
}
