package com.hepsiemlak.todo.util;

import com.hepsiemlak.todo.domain.model.Todo;

public class ToDoRepositoryUtil {

    public static Todo getTodo() {
        return Todo.builder()
                .userId("userId")
                .title("title")
                .build();
    }
}
