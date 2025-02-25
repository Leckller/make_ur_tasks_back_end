package com.backend.makeUrTasks.makeUrTasks.Interfaces;

import com.backend.makeUrTasks.makeUrTasks.abstractClasses.AbstractTask;

import java.util.ArrayList;
import java.util.Optional;

public interface TaskRepositoryInterface {

  public Optional<AbstractTask> findByTitle(String title, Integer userId);

  public Optional<AbstractTask> findById(Integer taskId, Integer userId);

  public Optional<ArrayList<AbstractTask>> find(Integer userId, int page);

  public Optional<AbstractTask> create(String title, Integer userId, String description);

  public void delete(Integer taskId);

  public Optional<AbstractTask> edit(String title, String description, Integer taskId);

}
