package com.backend.makeUrTasks.makeUrTasks.service.exceptions;

import org.apache.coyote.BadRequestException;

public class InvalidFieldsException extends BadRequestException {
  public InvalidFieldsException() {
    super("Username ou senha inválidos.");
  }
}
