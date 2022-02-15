package com.hepsiemlak.todo.service;

import com.hepsiemlak.todo.domain.dto.request.CreateUserRequest;
import com.hepsiemlak.todo.domain.dto.request.LoginRequest;
import com.hepsiemlak.todo.domain.dto.response.TokenResponse;
import com.hepsiemlak.todo.domain.model.AppUser;
import com.hepsiemlak.todo.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
public class AuthenticationService {

    private final AppUserRepository appUserRepository;
    private final TokenService tokenService;

    public TokenResponse login(LoginRequest loginRequest) {
        AppUser user = appUserRepository.getAppUserByEmail(loginRequest.getEmail());
        if (user == null || (user != null && !BCrypt.checkpw(loginRequest.getPassword(), user.getPassword()))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return tokenService.getToken(loginRequest.getEmail());
    }

    public void createUser(CreateUserRequest createUserRequest) {
        if (!appUserRepository.existsAppUserByEmail(createUserRequest.getEmail())) {
            appUserRepository.save(AppUser.builder()
                    .email(createUserRequest.getEmail())
                    .password(BCrypt.hashpw(createUserRequest.getPassword(), BCrypt.gensalt()))
                    .build());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
