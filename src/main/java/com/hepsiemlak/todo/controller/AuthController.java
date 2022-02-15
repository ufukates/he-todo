package com.hepsiemlak.todo.controller;

import com.hepsiemlak.todo.domain.dto.request.CreateUserRequest;
import com.hepsiemlak.todo.domain.dto.request.LoginRequest;
import com.hepsiemlak.todo.domain.dto.response.TokenResponse;
import com.hepsiemlak.todo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        TokenResponse tokenResponse = authenticationService.login(loginRequest);
        return ResponseEntity.ok().body(tokenResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        authenticationService.createUser(createUserRequest);
        return ResponseEntity.noContent().build();
    }
}
