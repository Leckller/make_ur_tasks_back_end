package com.backend.makeUrTasks.makeUrTasks.Models;

import com.backend.makeUrTasks.makeUrTasks.AbstractClasses.AbstractTask;
import com.backend.makeUrTasks.makeUrTasks.model.TaskModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TaskTestE2E Model Tests")
public class TaskModelTests {

  @Test
  @DisplayName("Testa a criação de uma tarefa")
  public void testCreateTask () {

    TaskModel model = new TaskModel();
    AbstractTask task = model.create("Test", 1, "De fato um teste");

    assertEquals(task, model.findById(1, 1));
  }

  @Test
  @DisplayName("Testa a edição de uma tarefa")
  public void testEditTask () {

    TaskModel model = new TaskModel();
    AbstractTask task = model.create("Teste", 1, "De fato um teste");

    assertEquals(task, model.findById(1, 1));

  }

}
