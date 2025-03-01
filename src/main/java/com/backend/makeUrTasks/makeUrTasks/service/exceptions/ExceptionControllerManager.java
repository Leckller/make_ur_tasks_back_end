package com.backend.makeUrTasks.makeUrTasks.service.exceptions;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerManager {

  @ExceptionHandler()
  public ResponseEntity<String> handleNotFound(NotFoundException exception) {
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(exception.getMessage());
  }

  @ExceptionHandler()
  public ResponseEntity<String> handleInvalidFields(BadRequestException exception) {
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
            .body("Erro interno não tratado - " + exception.getMessage());
  }


}
