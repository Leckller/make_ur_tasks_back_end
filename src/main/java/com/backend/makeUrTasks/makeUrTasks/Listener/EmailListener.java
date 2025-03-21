package com.backend.makeUrTasks.makeUrTasks.Listener;

import com.backend.makeUrTasks.makeUrTasks.repository.entity.Task;
import com.backend.makeUrTasks.makeUrTasks.repository.entity.User;
import com.backend.makeUrTasks.makeUrTasks.service.EmailService;
import jakarta.persistence.PostPersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {

  private EmailService emailService;

  @Autowired
  public EmailListener(EmailService emailService) {
    this.emailService = emailService;
  }

  @PostPersist
  public void userCreated(User user){
    String message = "Seja bem vindo %s!".formatted(user.getName());

    emailService.sendEmail(user.getEmail(), "Criação de conta - Make ur Tasks", message);
  }
}