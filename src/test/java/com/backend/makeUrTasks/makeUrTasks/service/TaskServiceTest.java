package com.backend.makeUrTasks.makeUrTasks.service;

import com.backend.makeUrTasks.makeUrTasks.abstractClasses.AbstractTask;
import com.backend.makeUrTasks.makeUrTasks.classes.Task;
import com.backend.makeUrTasks.makeUrTasks.exceptions.InvalidFieldsException;
import com.backend.makeUrTasks.makeUrTasks.repository.TaskRepository;
import com.backend.makeUrTasks.makeUrTasks.repository.TaskRepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
public class TaskServiceTest {

  @MockitoBean
  private TaskRepository mockedRepo;

  @Autowired
  private TaskService service;

  @Test
  @DisplayName("Testa a listagem de tarefas")
  void listTasksTest () {

    // Verifica se retorna um erro caso seja passado um id ou pagina invalida
    assertThrows(
      InvalidFieldsException.class,
      () -> service.listTasks(-1, 0)
    );

    assertThrows(
      InvalidFieldsException.class,
      () -> service.listTasks(1, -1)
    );

    // Verifica se retorna uma lista de tarefas caso os parâmetros estejam corretos.
    ArrayList<AbstractTask> mockedTaskList = new ArrayList<AbstractTask>();

    AbstractTask mockedTask = new Task("teste", "um belo teste", 1, 1);

    mockedTaskList.add(mockedTask);

    Mockito.when(
      mockedRepo.find(anyInt(), anyInt())
    ).thenReturn(Optional.of(mockedTaskList));

    List<AbstractTask> taskList = service.listTasks(1, 0);

    assertEquals(mockedTask.getId(), taskList.getFirst().getId());
    assertEquals(mockedTask.getTitle(), taskList.getFirst().getTitle());
    assertEquals(mockedTask.getUserId(), taskList.getFirst().getUserId());
    assertEquals(mockedTask.isFinished(), taskList.getFirst().isFinished());
    assertEquals(mockedTask.getDescription(), taskList.getFirst().getDescription());

    Mockito.verify(mockedRepo).find(eq(1), eq(0));

  }


  /**
   *
   */
  @Test
  @DisplayName("Deve retornar a tarefa correta, com os dados corretos")
  void titleStatusOk () {

    AbstractTask mockTask = new Task("Testando", "Um teste mockado", 1, 1);

    Mockito.when(
            mockedRepo.findByTitle(any(), any())
    ).thenReturn(Optional.of(mockTask));

    AbstractTask task = service.getTaskByTitle("Testando", 1);

    assertEquals("Testando", task.getTitle());
    assertEquals(1, task.getId());
    assertEquals("Um teste mockado", task.getDescription());

    Mockito.verify(mockedRepo).findByTitle(eq("Testando"), eq(1));

  }



}
