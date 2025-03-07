package com.backend.makeUrTasks.makeUrTasks.repository.entity;

import com.backend.makeUrTasks.makeUrTasks.controller.dto.User.UserCreationDto;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  private String email;
  private String password;

  @CreationTimestamp
  private Date createdAt;
  @UpdateTimestamp
  private Date updatedAt;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  List<Task> tasks;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  List<Tag> tags;

  public User() {}

  public User(UserCreationDto userCreationDto) {
    this.name = userCreationDto.name();
    this.email = userCreationDto.email();
    this.password = userCreationDto.password();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Task> getTasks() {
    return tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }
}
