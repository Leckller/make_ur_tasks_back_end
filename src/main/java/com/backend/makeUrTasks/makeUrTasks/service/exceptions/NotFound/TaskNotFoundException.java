package com.backend.makeUrTasks.makeUrTasks.service.exceptions.NotFound;

public class TaskNotFoundException extends NotFoundException {
    public TaskNotFoundException () {
        super("Tarefa não encontrada!");
    }
}
