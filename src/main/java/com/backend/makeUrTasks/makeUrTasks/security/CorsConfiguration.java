package com.backend.makeUrTasks.makeUrTasks.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/user")
        .allowedOrigins("http://localhost:8081")
        .allowedMethods("GET", "POST", "PATCH", "DELETE");
    registry.addMapping("/auth/**")
        .allowedOrigins("http://localhost:8081")
        .allowedMethods("GET", "POST", "PATCH", "DELETE");
    registry.addMapping("/task/**")
        .allowedOrigins("http://localhost:8081")
        .allowedMethods("GET", "POST", "PATCH", "DELETE");
  }
}