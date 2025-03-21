package com.backend.makeUrTasks.makeUrTasks.service;

import com.backend.makeUrTasks.makeUrTasks.controller.dto.Task.TaskCreationDto;
import com.backend.makeUrTasks.makeUrTasks.repository.TaskRepository;
import com.backend.makeUrTasks.makeUrTasks.repository.entity.Task;
import com.backend.makeUrTasks.makeUrTasks.repository.entity.User;
import com.backend.makeUrTasks.makeUrTasks.service.exceptions.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@DisplayName("Testes unitários da task service")
public class TaskServiceTest {

  @MockitoBean
  TaskRepository mockedTaskRepository;

  @MockitoBean
  UserService mockedUserService;

  @Autowired
  TaskService taskService;

  @Test
  @DisplayName("TC-01-CREATE-TASK: Testa a criação de uma tarefa")
  public void createTaskTest() throws UserNotFoundException {

    User mockedUser = this.mockedUser();
    Task mockedTask = this.mockedTask(1);

    Mockito
        .when(mockedUserService.findByUsername(any()))
        .thenReturn(mockedUser);

    Mockito
        .when(mockedTaskRepository.save(any()))
        .thenReturn(mockedTask);

    Task task = this.taskService.createTask(
        new TaskCreationDto("Um lindo teste"), "user-test"
    );

    assertEquals(mockedTask.getTitle(), task.getTitle());
    assertEquals(mockedTask.isFinished(), task.isFinished());
    assertEquals(mockedTask.getUser(), task.getUser());

  }

  @Test
  @DisplayName("TC-02-CREATE-TASK: Testa se retorna um exception caso não encontre um usuário")
  public void createTaskExceptionTest() throws UserNotFoundException {

    Mockito
        .when(this.mockedUserService.findByUsername(any()))
            .thenThrow(UserNotFoundException.class);

    assertThrows(
        UserNotFoundException.class,
        () -> this.taskService.createTask(
            new TaskCreationDto("Um lindo teste"), "user-test"
        )
    );

  }

  @Test
  @DisplayName("TC-01-LIST-TASK: Testa a listagem de tarefas")
  public void listCreatedTasks() {

    List<Task> mockedListOfTasks = new ArrayList<Task>();
    User mockedUser = this.mockedUser();

    for (int id = 1; id <= 20; id++) {
      mockedListOfTasks.add(this.mockedTask(id));
    }

    Page<Task> mockedPage = new PageImpl<Task>(
        mockedListOfTasks.stream().limit(15).toList(),
        PageRequest.of(0, 15),
        mockedListOfTasks.size());

    Mockito.when(this.mockedTaskRepository.findAllByUser(any(), any()))
        .thenReturn(mockedPage);

    Mockito.when(this.mockedUserService.loadUserByUsername("user-test"))
        .thenReturn(mockedUser);

    List<Task> listTasks = this.taskService.listTasks(0, "user-test");

    assertEquals(1, listTasks.getFirst().getId());
    assertEquals(15, listTasks.getLast().getId());
    assertEquals(15, listTasks.size());

  }

  @Test
  @DisplayName("TC-02-LIST-TASK: Testa se retorna um exception caso não encontre um usuário")
  public void listTaskExceptionTest() throws UserNotFoundException {

    Mockito
        .when(this.mockedUserService.loadUserByUsername(any()))
        .thenThrow(UserNotFoundException.class);

    assertThrows(
        UserNotFoundException.class,
        () -> this.taskService.listTasks(0, "definitivamente_inexistente")
    );

  }

  public Task mockedTask(Integer id) {
    Task mockedTask = new Task();
    mockedTask.setTitle("Um lindo teste");
    mockedTask.setId(id);
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
