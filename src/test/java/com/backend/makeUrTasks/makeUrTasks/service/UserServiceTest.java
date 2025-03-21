package com.backend.makeUrTasks.makeUrTasks.service;

import com.backend.makeUrTasks.makeUrTasks.controller.dto.User.UserCreationDto;
import com.backend.makeUrTasks.makeUrTasks.repository.UserRepository;
import com.backend.makeUrTasks.makeUrTasks.repository.entity.User;
import com.backend.makeUrTasks.makeUrTasks.service.exceptions.UserAlreadyExistsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@DisplayName("User Service Tests")
public class UserServiceTest {

  @Autowired
  UserService userService;

  @MockitoBean
  UserRepository mockedUserRepository;

  @Test
  @DisplayName("TC-01-FIND-BY-USERNAME: Testa se o metodo findByUserName lança uma exceção")
  public void findByUsernameShouldBeThrowException() {

    Mockito
        .when(this.mockedUserRepository.findByUsername(any()))
        .thenReturn(Optional.empty());

    assertThrows(
        UsernameNotFoundException.class,
        () -> this.userService.findByUsername("teste")
    );

    }

  @Test
  @DisplayName("TC-02-FIND-BY-USERNAME: Testa se o metodo findByUserName retorna uma pessoa")
  public void findByUsernameShouldBeReturnUser() {

    Mockito
        .when(this.mockedUserRepository.findByUsername(any()))
        .thenReturn(Optional.of(this.mockedUser("tester")));

    User user = this.userService.findByUsername("user-test");

    assertEquals("user-test", user.getUsername());
    assertEquals("tester", user.getName());
    assertEquals("USER", user.getRole());
    assertEquals("teste@teste.com", user.getEmail());
    assertTrue(new BCryptPasswordEncoder().matches("lalaland", user.getPassword()));

  }

  @Test
  @DisplayName("TC-01-USER-EXISTS: Testa se o metodo userExists lança uma exceção por email já registrado")
  public void userExistsByEmailShouldBeThrowException() {

    Mockito
        .when(this.mockedUserRepository.findByUsername(any()))
        .thenReturn(Optional.of(this.mockedUser("tester")));

    assertThrows(
        UserAlreadyExistsException.class,
        () -> this.userService.userExists("user-test", "teste@teste.com")
    );

  }

  @Test
  @DisplayName("TC-02-USER-EXISTS: Testa se o metodo userExists lança uma exceção por username já registrado")
  public void userExistsByUsernameShouldBeThrowException() {

    Mockito
        .when(this.mockedUserRepository.findByEmail(any()))
        .thenReturn(Optional.of(this.mockedUser("tester")));

    assertThrows(
        UserAlreadyExistsException.class,
        () -> this.userService.userExists("user-test", "teste@teste.com")
    );

  }

  @Test
  @DisplayName("TC-01-CREATE-USER: Testa se o metodo createUser lança uma exceção quando já existe um usuário com esse username ou email")
  public void userCreateShouldBeThrowException() {

    String mockedName = "tester-2";
    UserCreationDto mockedCreateUser = new UserCreationDto(
        mockedName,
        "user-test",
        "teste@teste.com",
        "beautifulTest"
    );

    Mockito
        .when(this.mockedUserRepository.findByUsername(any()))
        .thenReturn(Optional.of(this.mockedUser(mockedName)));

    assertThrows(
        UserAlreadyExistsException.class,
        () -> this.userService.createUser(mockedCreateUser)
    );

  }

  @Test
  @DisplayName("TC-02-CREATE-USER: Testa a criação de um usuário")
  public void userCreateTest() {

    String mockedName = "tester-2";
    UserCreationDto mockedCreateUser = new UserCreationDto(
        mockedName,
        "user-test",
        "teste@teste.com",
        "beautifulTest"
    );

    Mockito
        .when(this.mockedUserRepository.findByUsername(any()))
        .thenReturn(Optional.empty());
    Mockito
        .when(this.mockedUserRepository.findByEmail(any()))
        .thenReturn(Optional.empty());
    Mockito
        .when(this.mockedUserRepository.save(any()))
        .thenReturn(this.mockedUser(mockedName));

    User user = this.userService.createUser(mockedCreateUser);

    assertEquals(1, user.getId());
    assertEquals(mockedCreateUser.name(), user.getName());

  }

  public User mockedUser(String name) {
    User mockedUser = new User();
    mockedUser.setUsername("user-test");
    mockedUser.setName(name);
    mockedUser.setPassword(new BCryptPasswordEncoder().encode("lalaland"));
    mockedUser.setEmail("teste@teste.com");
    mockedUser.setId(1);

    return mockedUser;
  }

}
