package com.backend.makeUrTasks.makeUrTasks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerManager {

  @ExceptionHandler({
    UserNotFoundException.class,
    TaskNotFoundException.class
  })
  public ResponseEntity<String> handleUserNotFound(Exception exception) {
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(exception.getMessage());
  }

  @ExceptionHandler({
    InvalidFieldsException.class
  })
  public ResponseEntity<String> handleInvalidFields(Exception exception) {
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(exception.getMessage());
  }

  @ExceptionHandler({
          Exception.class
  })
  public ResponseEntity<String> handlerDefault(Exception exception) {
    return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(exception.getMessage());
  }


}
