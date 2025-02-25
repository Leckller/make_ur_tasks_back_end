package com.backend.makeUrTasks.makeUrTasks.repository;

import com.backend.makeUrTasks.makeUrTasks.abstractClasses.AbstractTask;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TaskTestE2E Model Tests")
public class TaskRepositoryTest {

  @Test
  @DisplayName("Testa a criação de uma tarefa")
  public void testCreateTask () {

    TaskRepository model = new TaskRepository();
    AbstractTask task = model.create("Test", 1, "De fato um teste")
      .orElseThrow(RuntimeException::new);

    AbstractTask taskInDb = model.findById(1, 1)
      .orElseThrow(RuntimeException::new);

    assertEquals(task, taskInDb);
  }

  @Test
  @DisplayName("Testa a edição de uma tarefa")
  public void testEditTask () {

    TaskRepository model = new TaskRepository();
    AbstractTask task = model.create("Teste", 1, "De fato um teste")
      .orElseThrow(RuntimeException::new);

    AbstractTask editTask = model.edit("Teste2", "Certamente um teste", 1)
      .orElseThrow(RuntimeException::new);

    AbstractTask taskInDb = model.findById(1, 1)
      .orElseThrow(RuntimeException::new);

    assertEquals("Teste2", taskInDb.getTitle());

  }

}
