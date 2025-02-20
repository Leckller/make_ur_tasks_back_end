package com.backend.makeUrTasks.makeUrTasks.Classes;

import com.backend.makeUrTasks.makeUrTasks.AbstractClasses.AbstractTask;

import java.util.Date;

public class Task extends AbstractTask {

  public Task(String title, String description, Integer id, Integer userId) {
    super(title, description, id, userId);
  }

}
