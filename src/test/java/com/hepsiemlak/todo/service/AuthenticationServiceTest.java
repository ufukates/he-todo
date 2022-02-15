package com.hepsiemlak.todo.service;

import com.hepsiemlak.todo.domain.dto.request.CreateUserRequest;
import com.hepsiemlak.todo.domain.dto.request.LoginRequest;
import com.hepsiemlak.todo.domain.dto.response.TokenResponse;
import com.hepsiemlak.todo.domain.model.AppUser;
import com.hepsiemlak.todo.repository.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static com.hepsiemlak.todo.util.AuthenticationServiceUtil.getAppUser;
import static com.hepsiemlak.todo.util.AuthenticationServiceUtil.getCreateUserRequest;
import static com.hepsiemlak.todo.util.AuthenticationServiceUtil.getLoginRequest;
import static com.hepsiemlak.todo.util.AuthenticationServiceUtil.getTokenResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class AuthenticationServiceTest {

    private AuthenticationService authenticationService;

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private TokenService tokenService;

    @BeforeEach
    public void setUp() {
        authenticationService = new AuthenticationService(appUserRepository, tokenService);
    }

    @Test
    public void should_login() {
        //Given
        final AppUser user = getAppUser();
        final LoginRequest loginRequest = getLoginRequest();
        final TokenResponse tokenResponse = getTokenResponse();

        //When
        Mockito.when(appUserRepository.getAppUserByEmail(loginRequest.getEmail())).thenReturn(user);
        Mockito.when(tokenService.getToken(loginRequest.getEmail())).thenReturn(tokenResponse);
        TokenResponse actualResponse = authenticationService.login(loginRequest);

        //Then
        assertEquals(tokenResponse, actualResponse);
    }

    @Test
    public void should_NotLogin() {
        //Given
        final String message = "401 UNAUTHORIZED";
        final LoginRequest loginRequest = getLoginRequest();
        final TokenResponse tokenResponse = getTokenResponse();

        //When
        Mockito.when(appUserRepository.getAppUserByEmail(loginRequest.getEmail())).thenReturn(null);
        Mockito.when(tokenService.getToken(loginRequest.getEmail())).thenReturn(tokenResponse);

        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            authenticationService.login(loginRequest);
        });
        //Then
        assertTrue(message.contains(exception.getMessage()));
    }

    @Test
    public void should_createUser() {
        //Given
        final CreateUserRequest createUserRequest = getCreateUserRequest();

        //When
        Mockito.when(appUserRepository.existsAppUserByEmail(createUserRequest.getEmail())).thenReturn(false);
        authenticationService.createUser(createUserRequest);

        //Then
        Mockito.verify(appUserRepository).save(any());
    }

    @Test
    public void should_notCreateUser() {
        //Given
        final String message = "400 BAD_REQUEST";
        final CreateUserRequest createUserRequest = getCreateUserRequest();

        //When
        Mockito.when(appUserRepository.existsAppUserByEmail(createUserRequest.getEmail())).thenReturn(true);

        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            authenticationService.createUser(createUserRequest);
        });

        //Then
       assertTrue(message.contains(exception.getMessage()));
    }
}
