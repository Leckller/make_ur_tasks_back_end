package com.backend.makeUrTasks.makeUrTasks.service.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionControllerManager {

  @ExceptionHandler()
  public ResponseEntity<String> handleNotFound(NotFoundException exception) {
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(exception.getMessage());
  }

  @ExceptionHandler()
  public ResponseEntity<String> handleAlreadyExists(AlreadyExistsException exception) {
    return ResponseEntity
        .status(HttpStatus.FORBIDDEN)
        .body(exception.getMessage());
  }


  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String> handleConstraintViolationError(ConstraintViolationException exception) {
    Map<String, String> errors = new HashMap<>();
    for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
      errors.put(violation.getPropertyPath().toString(), violation.getMessage());
    }
    return errors;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String> handleMethodArgumentNotValidError(MethodArgumentNotValidException exception) {
    Map<String, String> errors = new HashMap<>();
    for (FieldError error : exception.getBindingResult().getFieldErrors()) {
      errors.put(error.getField(), error.getDefaultMessage());
    }
    return errors;
  }

  @ExceptionHandler()
  public ResponseEntity<String> handleInvalidFields(BadRequestException exception) {
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(exception.getMessage());
  }

  @ExceptionHandler()
  public ResponseEntity<String> handlerDefault(Exception exception) {
    return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Erro interno não tratado - " + exception.getMessage());
  }


}
