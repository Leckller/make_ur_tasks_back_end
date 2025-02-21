package com.backend.makeUrTasks.makeUrTasks.model;

import com.backend.makeUrTasks.makeUrTasks.AbstractClasses.AbstractTask;
import com.backend.makeUrTasks.makeUrTasks.Classes.Task;
import com.backend.makeUrTasks.makeUrTasks.Interfaces.TaskModelInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class TaskModel implements TaskModelInterface {

  private ArrayList<AbstractTask> tasks = new ArrayList<AbstractTask>();
  protected Integer actualId = 1;

  /**
   */
  @Override
  public AbstractTask findByTitle(String title, Integer userId) {

    AbstractTask task = this.tasks.stream()
            .filter(t -> t.getTitle().equals(title) && t.getUserId() == userId)
            .findFirst()
            .orElse(null);

    return task;

  }

  /**
   */
  @Override
  public AbstractTask findById(Integer taskId, Integer userId) {

    AbstractTask task = this.tasks.stream()
            .filter(t -> t.getId() == taskId && t.getUserId() == userId)
            .findFirst()
            .orElse(null);

    return task;

  }

  /**
   */
  @Override
  public ArrayList<AbstractTask> find(Integer userId, int page) {

    Collection<AbstractTask> tasks = this.tasks
            .stream()
            .filter(t -> t.getUserId() == userId)
            .sorted((t, t2) -> t.getUpdatedAt().compareTo(t2.getUpdatedAt()))
            .limit(10)
            .skip(10L * page)
            .toList();

    return new ArrayList<AbstractTask>(tasks);

  }

  /**
   * Create a task.
   */
  @Override
  public AbstractTask create(String title, Integer userId, String description) {

    Task task = new Task(title, description, this.actualId, userId);

    this.actualId ++;

    this.tasks.add(task);

    return task;

  }

  /**
   *
   */
  @Override
  public void delete(Integer taskId) {

    this.tasks.remove(taskId);

  }

  /**
   */
  @Override
  public AbstractTask edit(String title, String description, Integer taskId) {

    AbstractTask findTask = this.tasks
            .stream()
            .filter(t -> t.getId() == taskId)
            .findFirst()
            .orElse(null);

    AbstractTask task = this.tasks.set(taskId, new Task(title, description, findTask.getId(), findTask.getUserId()));

    return task;
  }

}
