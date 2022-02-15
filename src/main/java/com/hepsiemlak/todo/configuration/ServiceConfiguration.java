package com.hepsiemlak.todo.configuration;

import com.hepsiemlak.todo.repository.AppUserRepository;
import com.hepsiemlak.todo.repository.ToDoItemRepository;
import com.hepsiemlak.todo.repository.ToDoRepository;
import com.hepsiemlak.todo.service.AppUserService;
import com.hepsiemlak.todo.service.AuthenticationService;
import com.hepsiemlak.todo.service.ToDoService;
import com.hepsiemlak.todo.service.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;

@Configuration
public class ServiceConfiguration {

    @Bean
    AppUserService appUserService(AppUserRepository appUserRepository) {
        return new AppUserService(appUserRepository);
    }

    @Bean
    ToDoService toDoService(ToDoRepository toDoRepository, ToDoItemRepository toDoItemRepository) {
        return new ToDoService(toDoRepository, toDoItemRepository);
    }

    @Bean
    public TokenService tokenService(KeyPair keyPair) {
        return new TokenService(keyPair);
    }

    @Bean
    AuthenticationService authenticationService(AppUserRepository appUserRepository, TokenService tokenService) {
        return new AuthenticationService(appUserRepository, tokenService);
    }
}
