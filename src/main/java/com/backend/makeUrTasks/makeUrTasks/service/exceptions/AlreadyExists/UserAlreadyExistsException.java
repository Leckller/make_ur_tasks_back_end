package com.backend.makeUrTasks.makeUrTasks.service.exceptions.AlreadyExists;

public class UserAlreadyExistsException extends AlreadyExistsException {
  public UserAlreadyExistsException() {
    super("Este usuário já existe!");
  }
}
