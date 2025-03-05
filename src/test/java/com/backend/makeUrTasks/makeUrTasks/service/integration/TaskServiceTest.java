package com.backend.makeUrTasks.makeUrTasks.service.integration;

import com.backend.makeUrTasks.makeUrTasks.controller.dto.Task.TaskCreationDto;
import com.backend.makeUrTasks.makeUrTasks.controller.dto.User.UserCreationDto;
import com.backend.makeUrTasks.makeUrTasks.repository.TaskRepository;
import com.backend.makeUrTasks.makeUrTasks.repository.UserRepository;
import com.backend.makeUrTasks.makeUrTasks.repository.entity.Task;
import com.backend.makeUrTasks.makeUrTasks.repository.entity.User;
import com.backend.makeUrTasks.makeUrTasks.service.TaskService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("prod")
public class TaskServiceTest {

  @Autowired
  private TaskService taskService;

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  MockMvc mockMvc;

  @Container
  public static MySQLContainer MYSQL_CONTAINER = new MySQLContainer("mysql:8.0.32")
      .withDatabaseName("MakeUrTasks");

  @DynamicPropertySource
  public static void overrideProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", MYSQL_CONTAINER::getJdbcUrl);
    registry.add("spring.datasource.username", MYSQL_CONTAINER::getUsername);
    registry.add("spring.datasource.password", MYSQL_CONTAINER::getPassword);
  }


  @Test
  @DisplayName("GET_BY_ID - Testa se lança um exception caso a tarefa esteja vazia")
  void getByIdNotFoundTest ()  {


  }

  @Test
  @DisplayName("Testa a listagem de tarefas")
  void listTasksTest () {
  }

  @Test
  @DisplayName("Deve retornar a tarefa correta, com os dados corretos")
  void titleStatusOk () throws Exception {

    Task task = makeTask("Damn bro");

    String url = "/task/1/user/1";

    mockMvc.perform(MockMvcRequestBuilders.get(url))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(task.getId()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(task.getTitle()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(task.getDescription()));

  }

  public Task makeTask(String title) {
    User user = makeUser();
    Task task = new Task(new TaskCreationDto(title, "descriptionTest", user.getId()), user);
    return this.taskRepository.save(task);
  }

  public User makeUser() {
    User user = new User(new UserCreationDto("nameTest", "emailTest@test.com", "passwordTest"));
    return this.userRepository.save(user);
  }

}
