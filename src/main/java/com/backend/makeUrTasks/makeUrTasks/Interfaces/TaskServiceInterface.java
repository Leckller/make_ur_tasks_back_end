package com.backend.makeUrTasks.makeUrTasks.Interfaces;

import com.backend.makeUrTasks.makeUrTasks.AbstractClasses.AbstractTask;

import java.util.ArrayList;

public interface TaskServiceInterface {

  public ArrayList<AbstractTask> getTasks(int userId);

  public AbstractTask createTask(
    String titulo,
    String description
  );

  public void deleteTask(int userId);

  public AbstractTask editTask(int userId, String titulo);

  public AbstractTask editTask(int userId, String titulo, String description);

}
