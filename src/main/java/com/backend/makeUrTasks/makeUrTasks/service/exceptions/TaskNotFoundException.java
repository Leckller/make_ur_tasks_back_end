package com.backend.makeUrTasks.makeUrTasks.service.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException () {
        super("Tarefa não encontrada!");
    }
}
