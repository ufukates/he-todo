package com.hepsiemlak.todo.util;

import com.hepsiemlak.todo.domain.model.TodoItem;

public class ToDoItemRepositoryUtil {

    public static TodoItem getTodoItem(){
        return TodoItem.builder()
                .title("title")
                .todoId("todoId")
                .status(false)
                .build();
    }
}
