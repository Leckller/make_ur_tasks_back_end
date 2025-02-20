package com.backend.makeUrTasks.makeUrTasks.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerManager {

  @ExceptionHandler
  public ResponseEntity<String> handleUserNotFoundException(
          UserNotFoundException exception) {
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(exception.getMessage());
  }

  @ExceptionHandler
  public ResponseEntity<String> handleInvalidFieldsException(
          InvalidFieldsException exception) {
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(exception.getMessage());
  }

  @ExceptionHandler
  public ResponseEntity<String> handlerDefaultException(
          Exception exception) {
    return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(exception.getMessage());
  }


}
