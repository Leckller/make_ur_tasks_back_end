package com.backend.makeUrTasks.makeUrTasks.controller;

import com.backend.makeUrTasks.makeUrTasks.controller.dto.AuthDto;
import com.backend.makeUrTasks.makeUrTasks.controller.dto.TokenDto;
import com.backend.makeUrTasks.makeUrTasks.service.TokenService;
import com.backend.makeUrTasks.makeUrTasks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin()
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;
  private final UserService userService;

  @Autowired
  public AuthController(AuthenticationManager authenticationManager, TokenService tokenService, UserService userService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
    this.userService = userService;
  }

  @PostMapping("/login")
  public TokenDto login(@RequestBody AuthDto authDto) {

    this.userService.findByUsername(authDto.username());

    UsernamePasswordAuthenticationToken usernamePassword =
        new UsernamePasswordAuthenticationToken(authDto.username(), authDto.password());

    Authentication auth = authenticationManager.authenticate(usernamePassword);
    String token = tokenService.generateToken(auth.getName());

    return new TokenDto(token);
  }

}
