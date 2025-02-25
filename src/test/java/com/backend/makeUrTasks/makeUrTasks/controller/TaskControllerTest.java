package com.backend.makeUrTasks.makeUrTasks.controller;

import com.backend.makeUrTasks.makeUrTasks.abstractClasses.AbstractTask;
import com.backend.makeUrTasks.makeUrTasks.classes.Task;
import com.backend.makeUrTasks.makeUrTasks.exceptions.TaskNotFoundException;
import com.backend.makeUrTasks.makeUrTasks.service.TaskService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Tests for Task Controller")
public class TaskControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private TaskService mockedService;

  @Test
  @DisplayName("Bucar tarefa pelo id")
  void getByIdTest() throws Exception {

    String url = "/task/id/1";

    AbstractTask mockedTask = new Task("Teste", "Teste", 1, 1);

    Mockito.when(
      mockedService.getTaskById(any(), any())
    ).thenReturn(mockedTask);

    mockMvc
      .perform(MockMvcRequestBuilders.get(url))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(mockedTask.getId()))
      .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(mockedTask.getTitle()))
      .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(mockedTask.getDescription()))
      .andExpect(MockMvcResultMatchers.jsonPath("$.finished").value(mockedTask.isFinished()));

  }

  @Test
  @DisplayName("Deve retornar not found caso a tarefa não exista")
  void getByIdNotFoundTest () throws Exception {

    String url = "/task/id/1";

    Mockito.when(
      mockedService.getTaskById(any(), any())
    ).thenThrow(
      new TaskNotFoundException()
    );

    mockMvc
      .perform(MockMvcRequestBuilders.get(url))
      .andExpect(MockMvcResultMatchers.status().isNotFound())
      .andExpect(MockMvcResultMatchers.content().string("Tarefa não encontrada!"));

  }

}
