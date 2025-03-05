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

  public Tag() {}

}
