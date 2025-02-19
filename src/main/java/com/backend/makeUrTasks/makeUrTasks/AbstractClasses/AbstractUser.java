package com.backend.makeUrTasks.makeUrTasks.AbstractClasses;

public abstract class AbstractUser {

  private String name;
  private String password;
  private String email;

  public String getName() {
    return this.name;
  };

  public String getPassword() {
    return this.password;
  };

  public String getEmail() {
    return this.email;
  };

  public void setEmail(String email) {
    this.email = email;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
