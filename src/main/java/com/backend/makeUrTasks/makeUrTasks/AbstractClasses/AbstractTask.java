package com.backend.makeUrTasks.makeUrTasks.AbstractClasses;

import java.util.Date;

/**
 * Interface da tarefa
 */
public abstract class AbstractTask {

  private float id;
  private float userId;

  private String title;
  private boolean finished;

  private Date createdAt;
  private Date updatedAt;

  private String description;
  private String conclusionNotes;

  public AbstractTask(String title, String description, float id, float userId) {
    this.title = title;
    this.description = description;
    this.createdAt = new Date();
    this.id = id;
    this.userId = userId;
  }

  public float getUserId() {
    return userId;
  }

  public void setUserId(float userId) {
    this.userId = userId;
  }

  public float getId() {
    return id;
  }

  public void setId(float id) {
    this.id = id;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public boolean isFinished() {
    return finished;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public String getConclusionNotes() {
    return conclusionNotes;
  }

  public String getDescription() {
    return description;
  }

  public String getTitulo() {
    return title;
  }

  public void setConclusionNotes(String conclusionNotes) {
    this.conclusionNotes = conclusionNotes;
  }

  public void setTitulo(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setFinished() {
    this.finished = !this.finished;
  }

}
