package com.hepsiemlak.todo.util;

import com.hepsiemlak.todo.domain.model.AppUser;

public class AppUserRepositoryUtil {

    public static AppUser getAppUser() {
        return AppUser.builder()
                .email("test@test.com")
                .password("$2a$10$DUr7ZZUZJucOyMoaAlJJx.BwBi5P3UqYSlILVubWiIRz7peHb46.2")
                .build();
    }
}
