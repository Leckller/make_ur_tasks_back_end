package com.backend.makeUrTasks.makeUrTasks.repository;

import com.backend.makeUrTasks.makeUrTasks.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> { }
