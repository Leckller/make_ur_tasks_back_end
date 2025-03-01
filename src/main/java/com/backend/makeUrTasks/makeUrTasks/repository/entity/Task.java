package com.backend.makeUrTasks.makeUrTasks.repository.entity;

import com.backend.makeUrTasks.makeUrTasks.controller.dto.Task.TaskCreationDto;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;


@Entity
@Table(name = "tasks")
public class Task  {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String title;
  private boolean finished = false;

  @CreationTimestamp
  private Date createdAt;
  @UpdateTimestamp
  private Date updatedAt;

  private String description;
  private String conclusionNotes;

  @ManyToOne(optional = false)
  // Torna essa coluna responsável pelo relocionamento.
  @JoinColumn(name = "user_id")
  User user;

  public Task(String title, String description, Integer id) {
    this.title = title;
    this.description = description;
    this.createdAt = new Date();
    this.updatedAt = new Date();
  }

  public Task(TaskCreationDto taskCreationDto, User user) {
    this.title = taskCreationDto.title;
    this.description = taskCreationDto.description;
    this.user = user;
  }

  public User getUser() { return user; }

  public void setUser(User user) { this.user = user; }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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

  public String getTitle() {
    return title;
  }

  public void setConclusionNotes(String conclusionNotes) {
    this.conclusionNotes = conclusionNotes;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setFinished() {
    this.finished = !this.finished;
  }

}
