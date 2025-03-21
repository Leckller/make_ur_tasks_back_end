package com.backend.makeUrTasks.makeUrTasks.security.audit;

import com.backend.makeUrTasks.makeUrTasks.repository.entity.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareUser implements AuditorAware<String> {
  @Override
  public Optional<String> getCurrentAuditor() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null){
      return Optional.of("unknown");
    }
    User auditor = (User) auth.getPrincipal();
    return Optional.of(auditor.getUsername());
  }
}