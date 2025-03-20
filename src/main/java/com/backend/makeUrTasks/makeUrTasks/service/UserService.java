package com.backend.makeUrTasks.makeUrTasks.service;

import com.backend.makeUrTasks.makeUrTasks.controller.dto.User.UserCreationDto;
import com.backend.makeUrTasks.makeUrTasks.repository.UserRepository;
import com.backend.makeUrTasks.makeUrTasks.repository.entity.User;
import com.backend.makeUrTasks.makeUrTasks.service.exceptions.UserNotFoundException;
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

  public User findUserById(Integer userId)
      throws UserNotFoundException {

    return this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

  }

  public User createUser(UserCreationDto userCreationDto) {

    String hashedPassword = new BCryptPasswordEncoder()
        .encode(userCreationDto.password());

    User user = new User(userCreationDto);
    user.setPassword(hashedPassword);

    return this.userRepository.save(user);

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
