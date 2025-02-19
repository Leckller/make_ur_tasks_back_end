package com.backend.makeUrTasks.makeUrTasks.Interfaces;

import com.backend.makeUrTasks.makeUrTasks.AbstractClasses.AbstractTask;

import java.util.ArrayList;

public interface TaskModelInterface {

  public AbstractTask findByTitle(String title, float userId);

  public AbstractTask findById(float taskId, float userId);

  public ArrayList<AbstractTask> find(float userId, int page);

  public AbstractTask create(String title, float userId, String description);

  public void delete(float taskId);

  public AbstractTask edit(String title, String description, float taskId);

}
