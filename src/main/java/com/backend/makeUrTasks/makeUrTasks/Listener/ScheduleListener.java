package com.backend.makeUrTasks.makeUrTasks.Listener;

import com.backend.makeUrTasks.makeUrTasks.repository.entity.Task;
import com.backend.makeUrTasks.makeUrTasks.service.EmailService;
import jakarta.persistence.PostPersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleListener {

  private EmailService emailService;

  @Autowired
  public ScheduleListener(EmailService emailService) {
    this.emailService = emailService;
  }

  @PostPersist
  public void postUpdate(Task task){
    String message = String.format("Olha só o bglh funcionando" + task.getTitle());

    emailService.sendEmail("gusttavoruynascimento@gmail.com", "testando", message);
  }
}