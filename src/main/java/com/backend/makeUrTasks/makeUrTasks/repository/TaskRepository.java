package com.backend.makeUrTasks.makeUrTasks.repository;

import com.backend.makeUrTasks.makeUrTasks.repository.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> { }
