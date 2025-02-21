package com.backend.makeUrTasks.makeUrTasks.Interfaces;

import com.backend.makeUrTasks.makeUrTasks.AbstractClasses.AbstractTask;

import java.util.ArrayList;

public interface TaskModelInterface {

  public AbstractTask findByTitle(String title, Integer userId);

  public AbstractTask findById(Integer taskId, Integer userId);

  public ArrayList<AbstractTask> find(Integer userId, int page);

  public AbstractTask create(String title, Integer userId, String description);

  public void delete(Integer taskId);

  public AbstractTask edit(String title, String description, Integer taskId);

}
