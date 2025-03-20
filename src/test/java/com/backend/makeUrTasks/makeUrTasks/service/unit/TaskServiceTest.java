package com.backend.makeUrTasks.makeUrTasks.service.unit;

import com.backend.makeUrTasks.makeUrTasks.controller.dto.Task.TaskCreationDto;
import com.backend.makeUrTasks.makeUrTasks.repository.TaskRepository;
import com.backend.makeUrTasks.makeUrTasks.repository.UserRepository;
import com.backend.makeUrTasks.makeUrTasks.repository.entity.Task;
import com.backend.makeUrTasks.makeUrTasks.repository.entity.User;
import com.backend.makeUrTasks.makeUrTasks.service.TaskService;
import com.backend.makeUrTasks.makeUrTasks.service.exceptions.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@DisplayName("Testes unitários da task service")
public class TaskServiceTest {

  @MockitoBean
  TaskRepository mockedTaskRepository;

  @MockitoBean
  UserRepository mockedUserRepository;

  @Autowired
  TaskService taskService;

  @Test
  @DisplayName("TC-01-CREATE-TASK: Testa a criação de uma tarefa")
  public void createTaskTest() throws UserNotFoundException {

    User mockedUser = this.mockedUser();
    Task mockedTask = this.mockedTask();

    Mockito
        .when(mockedUserRepository.findByUsername(any()))
        .thenReturn(Optional.of(mockedUser));

    Mockito
        .when(mockedTaskRepository.save(any()))
        .thenReturn(mockedTask);

    Task task = this.taskService.createTask(
        new TaskCreationDto("Um lindo teste", "user-test")
    );

    assertEquals(mockedTask.getTitle(), task.getTitle());
    assertEquals(mockedTask.isFinished(), task.isFinished());
    assertEquals(mockedTask.getUser(), task.getUser());

  }

  @Test
  @DisplayName("TC-02-CREATE-TASK: Testa se retorna um exception caso não encontre um usuário")
  public void createTaskExceptionTest() throws UserNotFoundException {

    Task task = this.taskService.createTask(
        new TaskCreationDto("Um lindo teste", "user-test")
    );

    Mockito
        .when(this.mockedUserRepository.findByUsername(any()))
        .thenReturn(Optional.empty());

    assertThrows(
        UserNotFoundException.class,
        () -> this.taskService.createTask(
            new TaskCreationDto("Um lindo teste", "user-test")
        )
    );

  }

  public Task mockedTask() {
    Task mockedTask = new Task();
    mockedTask.setTitle("Um lindo teste");
    mockedTask.setId(1);
    mockedTask.setUser(this.mockedUser());

    return mockedTask;
  }

  public User mockedUser() {
    User mockedUser = new User();
    mockedUser.setUsername("user-test");
    mockedUser.setName("tester");
    mockedUser.setPassword(new BCryptPasswordEncoder().encode("lalaland"));
    mockedUser.setEmail("teste@teste.com");
    mockedUser.setRole("USER");

    return mockedUser;
  }

}
