package com.backend.makeUrTasks.makeUrTasks.model;

import com.backend.makeUrTasks.makeUrTasks.AbstractClasses.AbstractTask;
import com.backend.makeUrTasks.makeUrTasks.Classes.Task;
import com.backend.makeUrTasks.makeUrTasks.Exceptions.NotFoundException;
import com.backend.makeUrTasks.makeUrTasks.Interfaces.TaskModelInterface;

import java.util.ArrayList;
import java.util.Collection;

public class TaskModel implements TaskModelInterface {

  private ArrayList<AbstractTask> tasks = new ArrayList<AbstractTask>();
  protected float actualId = 0;

  /**
   */
  @Override
  public AbstractTask findByTitle(String title, float userId) {

    AbstractTask task = this.tasks.stream()
            .filter(t -> t.getTitulo().equals(title) && t.getUserId() == userId)
            .findFirst()
            .orElse(null);

    return task;

  }

  /**
   */
  @Override
  public AbstractTask findById(float taskId, float userId) {

    AbstractTask task = this.tasks.stream()
            .filter(t -> t.getId() == taskId && t.getUserId() == userId)
            .findFirst()
            .orElse(null);

    return task;

  }

  /**
   */
  @Override
  public ArrayList<AbstractTask> find(float userId, int page) {

    Collection<AbstractTask> tasks = this.tasks
            .stream()
            .filter(t -> t.getUserId() == userId)
            .sorted((t, t2) -> t.getUpdatedAt().compareTo(t2.getUpdatedAt()))
            .limit(10)
            .skip(10 * page)
            .toList();

    return new ArrayList<AbstractTask>(tasks);

  }

  /**
   */
  @Override
  public AbstractTask create(String title, float userId, String description) {

    Task task = new Task(title, description, this.actualId, userId);

    this.actualId ++;

    return task;

  }

  /**
   *
   */
  @Override
  public void delete(float taskId) {

    this.tasks.remove(taskId);

  }

  /**
   */
  @Override
  public AbstractTask edit(String title, String description, float taskId) {

    AbstractTask findTask = this.tasks
            .stream()
            .filter(t -> t.getId() == taskId)
            .findFirst()
            .orElse(null);

    AbstractTask task = this.tasks.set(Float.floatToIntBits(taskId), new Task(title, description, findTask.getId(), findTask.getUserId()));

    return task;
  }

}
