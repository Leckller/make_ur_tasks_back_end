package com.backend.makeUrTasks.makeUrTasks.service.exceptions;

public class TaskNotFoundException extends NotFoundException {
    public TaskNotFoundException () {
        super("Tarefa não encontrada!");
    }
}
