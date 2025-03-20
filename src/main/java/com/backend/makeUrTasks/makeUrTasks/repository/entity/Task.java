package com.backend.makeUrTasks.makeUrTasks.repository.entity;

import com.backend.makeUrTasks.makeUrTasks.Listener.ScheduleListener;
import com.backend.makeUrTasks.makeUrTasks.controller.dto.Task.TaskCreationDto;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "tasks")
@EntityListeners(ScheduleListener.class)
public class Task  {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String title;
  private boolean finished = false;

  @CreationTimestamp
  private Date createdAt = new Date();
  @UpdateTimestamp
  private Date updatedAt = new Date();

  // Torna essa coluna responsável pelo relocionamento.
  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id")
  User user;

  public Task() {}

  public Task(TaskCreationDto taskCreationDto) {
    this.title = taskCreationDto.title();
  }

  // From json
  public Task(Integer id, String title, Boolean finished, Date created_at, Date updated_at) {
    this.id = id;
    this.title = title;
    this.finished = finished;
    this.createdAt = created_at;
    this.updatedAt = updated_at;
  }

  public Task(TaskCreationDto taskCreationDto, User user) {
    this.title = taskCreationDto.title();
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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setFinished() {
    this.finished = !this.finished;
  }

  public void setFinished(boolean finished) { this.finished = finished; }

  public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

  public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

}
