package com.backend.makeUrTasks.makeUrTasks.repository.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Tag {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String Color;
  private String Name;

  @ManyToOne(optional = false)
  @JoinColumn(name = "tag_id")
  User user;

  @ManyToMany
  @JoinTable(
      uniqueConstraints = @UniqueConstraint(columnNames = {"tag_id", "post_id"}),
      name = "tasks_tags",
      joinColumns = @JoinColumn(name = "tag_id"),
      inverseJoinColumns = @JoinColumn(name = "task_id")
  )
  private List<Task> tasks;

  public Integer getId() { return id; }

  public void setId(Integer id) { this.id = id; }

  public String getColor() { return Color; }

  public void setColor(String color) { Color = color; }

  public String getName() { return Name; }

  public void setName(String name) { Name = name; }

  public User getUser() { return user; }

  public void setUser(User user) { this.user = user; }

  public List<Task> getTasks() { return tasks; }

  public void setTasks(List<Task> tasks) { this.tasks = tasks; }

  public Tag() {}

}
