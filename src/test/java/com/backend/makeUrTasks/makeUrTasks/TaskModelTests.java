package com.backend.makeUrTasks.makeUrTasks;

import com.backend.makeUrTasks.makeUrTasks.AbstractClasses.AbstractTask;
import com.backend.makeUrTasks.makeUrTasks.model.TaskModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Task Model Tests")
public class TaskModelTests {

  @Test
  @DisplayName("Testa a criação de um usuário")
  public void testeCreateNewUser () {

    TaskModel model = new TaskModel();
    AbstractTask task = model.create("Teste", 1, "De fato um teste");

  }

}
