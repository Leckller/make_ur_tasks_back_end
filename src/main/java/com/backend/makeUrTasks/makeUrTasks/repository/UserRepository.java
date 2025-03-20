package com.backend.makeUrTasks.makeUrTasks.repository;

import com.backend.makeUrTasks.makeUrTasks.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByUsername(String username);
}
