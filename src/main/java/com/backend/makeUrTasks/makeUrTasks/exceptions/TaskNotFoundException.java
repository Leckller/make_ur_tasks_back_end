package com.backend.makeUrTasks.makeUrTasks.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException () {
        super("Tarefa não encontrada!");
    }
}
