package com.hepsiemlak.todo.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserType {

    USER("SCOPE_USER");

    private final String authority;
}
