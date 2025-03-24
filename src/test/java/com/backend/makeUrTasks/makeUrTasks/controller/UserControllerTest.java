package com.backend.makeUrTasks.makeUrTasks.controller;

import com.backend.makeUrTasks.makeUrTasks.controller.dto.TokenDto;
import com.backend.makeUrTasks.makeUrTasks.controller.dto.User.UserCreationDto;
import com.backend.makeUrTasks.makeUrTasks.repository.entity.User;
import com.backend.makeUrTasks.makeUrTasks.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("User controller Test")
public class UserControllerTest {

  ObjectMapper objectMapper = new ObjectMapper();

  @MockitoBean
  UserService mockedUserService;

  @Autowired
  MockMvc mockMvc;

  @Test
  @DisplayName("Cria um novo usuário")
  public void createUserTest() throws Exception {
    UserCreationDto userDto = new UserCreationDto("tester", "user-test", "teste@teste.com", "aGoodPassword");
    String content = objectMapper.writeValueAsString(userDto);

    Mockito
        .when(this.mockedUserService.createUser(any()))
        .thenReturn(new User(userDto));

    MvcResult result= mockMvc.
        perform(MockMvcRequestBuilders.post("/user")
                .contentType("application/json")
                .content(content))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andReturn();

    String json = result.getResponse().getContentAsString();
    TokenDto response = objectMapper.readValue(json, TokenDto.class);

    assertInstanceOf(String.class, response.token());

  }

}
