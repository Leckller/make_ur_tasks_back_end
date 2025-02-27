package com.backend.makeUrTasks.makeUrTasks.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskServiceTest {

  @Autowired
  private TaskService service;

  @Test
  @DisplayName("GET_BY_ID - Testa se lança um exception caso a tarefa esteja vazia")
  void getByIdNotFoundTest () {



  }

  @Test
  @DisplayName("Testa a listagem de tarefas")
  void listTasksTest () {


  }


  /**
   *
   */
  @Test
  @DisplayName("Deve retornar a tarefa correta, com os dados corretos")
  void titleStatusOk () {



  }



}
