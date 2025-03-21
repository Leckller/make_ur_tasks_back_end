package com.backend.makeUrTasks.makeUrTasks.service;

import com.backend.makeUrTasks.makeUrTasks.controller.dto.User.UserCreationDto;
import com.backend.makeUrTasks.makeUrTasks.repository.UserRepository;
import com.backend.makeUrTasks.makeUrTasks.repository.entity.User;
import com.backend.makeUrTasks.makeUrTasks.service.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User createUser(UserCreationDto userCreationDto) {

    this.userExists(userCreationDto.username(), userCreationDto.email());

    String hashedPassword = new BCryptPasswordEncoder()
        .encode(userCreationDto.password());

    User user = new User();

    user.setName(userCreationDto.name());
    user.setUsername(userCreationDto.username());
    user.setEmail(userCreationDto.email());
    user.setPassword(hashedPassword);

    return this.userRepository.save(user);

  }

  public void userExists(String username, String email) throws UsernameNotFoundException  {
    if (this.userRepository.findByUsername(username).isPresent() || this.userRepository.findByEmail(email).isPresent()) {
        throw new UserAlreadyExistsException();
    }
  }

  public User findUserByUsername(String username) throws UsernameNotFoundException {
    return this.userRepository
        .findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));
  }

  /**
   * Encontra um usuário pelo nome
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return this.userRepository
        .findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));
  }
}
