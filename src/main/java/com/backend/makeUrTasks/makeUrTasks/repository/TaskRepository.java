package com.backend.makeUrTasks.makeUrTasks.repository;

import com.backend.makeUrTasks.makeUrTasks.abstractClasses.AbstractTask;
import com.backend.makeUrTasks.makeUrTasks.classes.Task;
import com.backend.makeUrTasks.makeUrTasks.Interfaces.TaskRepositoryInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Component
public class TaskRepository implements TaskRepositoryInterface {

  private ArrayList<AbstractTask> tasks = new ArrayList<AbstractTask>();
  protected Integer actualId = 1;

  /**
   */
  @Override
  public Optional<AbstractTask> findByTitle(String title, Integer userId) {

    AbstractTask task = this.tasks.stream()
            .filter(t -> t.getTitle().equals(title) && t.getUserId() == userId)
            .findFirst()
            .orElse(null);

    return Optional.ofNullable(task);

  }

  /**
   */
  @Override
  public Optional<AbstractTask> findById(Integer taskId, Integer userId) {

    return this.tasks.stream()
            .filter(t -> Objects.equals(t.getId(), taskId) && Objects.equals(t.getUserId(), userId))
            .findFirst();

  }

  /**
   */
  @Override
  public Optional<ArrayList<AbstractTask>> find(Integer userId, int page) {

    Collection<AbstractTask> tasks = this.tasks
            .stream()
            .filter(t -> t.getUserId() == userId)
            .sorted((t, t2) -> t.getUpdatedAt().compareTo(t2.getUpdatedAt()))
            .skip(10 * page)
            .limit(10)
            .toList();

    return Optional.ofNullable(new ArrayList<AbstractTask>(tasks));

  }

  /**
   * Create a task.
   */
  @Override
  public Optional<AbstractTask> create(String title, Integer userId, String description) {

    Task task = new Task(title, description, this.actualId, userId);

    this.actualId ++;

    this.tasks.add(task);

    return Optional.ofNullable(task);

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
  public Optional<AbstractTask> edit(String title, String description, Integer taskId) {

    AbstractTask findTask = this.tasks
            .stream()
            .filter(t -> t.getId() == taskId)
            .findFirst()
            .orElse(null);

    AbstractTask task = this.tasks.set(taskId, new Task(title, description, findTask.getId(), findTask.getUserId()));

    return Optional.ofNullable(task);
  }

}
