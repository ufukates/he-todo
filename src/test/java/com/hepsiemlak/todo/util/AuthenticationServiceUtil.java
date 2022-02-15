package com.hepsiemlak.todo.util;

import com.hepsiemlak.todo.domain.dto.request.CreateUserRequest;
import com.hepsiemlak.todo.domain.dto.request.LoginRequest;
import com.hepsiemlak.todo.domain.dto.response.TokenResponse;
import com.hepsiemlak.todo.domain.model.AppUser;

public class AuthenticationServiceUtil {

    public static LoginRequest getLoginRequest() {
        return LoginRequest.builder()
                .email("test@test.com")
                .password("asd123")
                .build();
    }

    public static AppUser getAppUser() {
        return AppUser.builder()
                .id("userId")
                .email("test@test.com")
                .password("$2a$10$DUr7ZZUZJucOyMoaAlJJx.BwBi5P3UqYSlILVubWiIRz7peHb46.2")
                .build();
    }

    public static TokenResponse getTokenResponse() {
        return TokenResponse.builder()
                .accessToken("accessToken")
                .tokenType("bearer")
                .expiresIn(123L)
                .build();
    }

    public static CreateUserRequest getCreateUserRequest(){
        return CreateUserRequest.builder()
                .email("test@test.com")
                .password("password")
                .build();
    }
}
