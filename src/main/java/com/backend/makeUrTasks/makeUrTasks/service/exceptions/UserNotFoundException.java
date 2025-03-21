package com.backend.makeUrTasks.makeUrTasks.service.exceptions;

public class UserNotFoundException extends NotFoundException {
  public UserNotFoundException () {
    super("Usuário não encontrado!");
  }
}
