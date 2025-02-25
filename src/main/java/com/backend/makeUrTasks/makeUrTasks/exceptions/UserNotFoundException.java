package com.backend.makeUrTasks.makeUrTasks.exceptions;

public class UserNotFoundException extends RuntimeException{

  public UserNotFoundException(String message) {
    super(message);
  }

}
