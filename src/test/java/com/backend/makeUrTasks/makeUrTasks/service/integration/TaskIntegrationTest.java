package com.backend.makeUrTasks.makeUrTasks.service.integration;

import com.backend.makeUrTasks.makeUrTasks.controller.dto.Task.TaskCreationDto;
import com.backend.makeUrTasks.makeUrTasks.controller.dto.Task.TaskResponseDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("prod")
public class TaskIntegrationTest {

  @Autowired
  private TaskService taskService;

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  MockMvc mockMvc;

  ObjectMapper objectMapper = new ObjectMapper();

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
  @DisplayName("CREATE - Cria uma tarefa e espera o resultado correto")
  void createTaskTest() throws Exception {
    User user = makeUser("createUser@test.com");
    TaskCreationDto task = TaskCreationDto.makeDto("Tarefa nova", "Uma linda descrição", user.getId());
    String content = objectMapper.writeValueAsString(task);
    String url = "/task";

    MvcResult result = mockMvc
        .perform(
            post(url)
                .contentType("application/json")
                .content(content))
        .andExpect(status().isCreated())
        .andReturn();

    String json = result.getResponse().getContentAsString();
    TaskResponseDto response = TaskResponseDto.fromEntity(objectMapper.readValue(json, Task.class));

    assertEquals(1, response.id());
    assertEquals("Tarefa nova", response.title());
    assertEquals("Uma linda descrição", response.description());

  }


  @Test
  @DisplayName("GET_BY_ID - Testa se retorna uma tarefa pelo id")
  void getByIdNotFoundTest () throws Exception {
    User user = makeUser("getByIdUser@test.com");
    Task task = makeTask(user, "get by id test task");
    String url = "/task/" + Integer.toString(task.getId()) + "/user/" + Integer.toString(user.getId());

    MvcResult result = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
    String json = result.getResponse().getContentAsString();
    TaskResponseDto response = TaskResponseDto.fromEntity(objectMapper.readValue(json, Task.class));

    assertEquals(TaskResponseDto.fromEntity(task), response);

  }

  @Test
  @DisplayName("LIST - Testa a listagem de tarefas")
  void listTasksTest () throws Exception {
    User user = makeUser("listUser@test.com");
    List<TaskResponseDto> response;
    MvcResult result;
    String url = "/task/list/1";

    for (int i = 0; i < 5; i++) {
      Task task = makeTask(user,"Damn bro" + Integer.toString(i));
    }

    result = mockMvc.perform(get(url))
        .andExpect(status().isOk())
        .andReturn();

    String json = result.getResponse().getContentAsString();
    response = objectMapper.readValue(json, new TypeReference<List<Task>>(){});

    assertEquals(5, response.size());
    assertNotNull(response);

  }

  public Task makeTask(User user, String title) {
    Task task = new Task(new TaskCreationDto(title, "descriptionTest", user.getId()), user);
    return this.taskRepository.save(task);
  }

  public User makeUser(String email) {
    User user = new User(new UserCreationDto("nameTest", email, "passwordTest"));
    return this.userRepository.save(user);
  }

}
