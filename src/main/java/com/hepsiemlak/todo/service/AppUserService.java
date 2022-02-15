package com.hepsiemlak.todo.service;

import com.hepsiemlak.todo.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getUserEmail() {
        return getAuthentication().getName();
    }

    public String getUserId() {
        return appUserRepository.getAppUserByEmail(getUserEmail()).getId();
    }
}
