package com.backend.makeUrTasks.makeUrTasks.service.exceptions;

public class UserAlreadyExistsException extends AlreadyExistsException {
  public UserAlreadyExistsException() {
    super("Este usuário já está sendo usado!");
  }
}
