package com.backend.makeUrTasks.makeUrTasks.E2E;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
public class TaskTestE2E {

  @Autowired
  private MockMvc mock;

  @Test
  @DisplayName("Deve retornar o status code Ok ( 200 ) ao pesquisar pelas tarefas")
  void listStatusOk () throws Exception {

    this.mock.perform(get("/task/list/0"))
            .andExpect(status().is(200));

  }

  @Test
  @DisplayName("Deve retornar o status code Ok ( 200 ) ao pesquisar pelo nome de uma tarefa")
  void titleStatusOk () throws Exception {

    this.mock.perform(get("/task/title/aaa"))
            .andExpect(status().is(200));

  }



}
