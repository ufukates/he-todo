package com.hepsiemlak.todo.util;

import com.hepsiemlak.todo.domain.dto.request.CreateToDoItemRequest;
import com.hepsiemlak.todo.domain.dto.request.CreateToDoRequest;
import com.hepsiemlak.todo.domain.dto.request.UpdateToDoItemRequest;
import com.hepsiemlak.todo.domain.dto.request.UpdateToDoRequest;
import com.hepsiemlak.todo.domain.dto.response.ToDoItemResponse;
import com.hepsiemlak.todo.domain.dto.response.ToDoResponse;
import com.hepsiemlak.todo.domain.model.Todo;
import com.hepsiemlak.todo.domain.model.TodoItem;

import java.util.Arrays;
import java.util.List;

public class ToDoServiceUtil {

    public static ToDoResponse getTodoResponse() {
        return ToDoResponse.builder()
                .id("id")
                .title("title")
                .build();
    }

    public static List<ToDoResponse> getTodosResponse() {
        return Arrays.asList(ToDoResponse.builder()
                        .id("id")
                        .title("title")
                        .build(),
                ToDoResponse.builder()
                        .id("id2")
                        .title("title2")
                        .build());
    }

    public static Todo getTodo() {
        return Todo.builder()
                .title("title")
                .userId("userId")
                .build();
    }

    public static Todo getTodoWithId() {
        return Todo.builder()
                .id("id")
                .title("title")
                .userId("userId")
                .build();
    }

    public static List<Todo> getTodos() {
        return Arrays.asList(Todo.builder()
                        .id("id")
                        .title("title")
                        .userId("userId")
                        .build(),
                Todo.builder()
                        .id("id2")
                        .title("title2")
                        .userId("userId")
                        .build());
    }

    public static CreateToDoRequest getCreateToDoRequest() {
        return CreateToDoRequest.builder()
                .title("title")
                .build();
    }

    public static UpdateToDoRequest getUpdateToDoRequest() {
        return UpdateToDoRequest.builder()
                .id("id")
                .title("title update")
                .build();
    }

    public static ToDoItemResponse getToDoItemResponse() {
        return ToDoItemResponse.builder()
                .id("id")
                .todoId("todoId")
                .title("title")
                .status(false)
                .build();
    }

    public static List<ToDoItemResponse> getToDoItemsResponse() {
        return Arrays.asList(ToDoItemResponse.builder()
                        .id("id")
                        .todoId("todoId")
                        .title("title")
                        .status(false)
                        .build(),
                ToDoItemResponse.builder()
                        .id("id2")
                        .todoId("todoId")
                        .title("title2")
                        .status(true)
                        .build());
    }

    public static TodoItem getTodoItem() {
        return TodoItem.builder()
                .todoId("todoId")
                .title("title")
                .status(false)
                .build();
    }

    public static TodoItem getTodoItemWithId() {
        return TodoItem.builder()
                .id("id")
                .todoId("todoId")
                .title("title")
                .status(false)
                .build();
    }

    public static List<TodoItem> getTodoItems() {
        return Arrays.asList(TodoItem.builder()
                        .id("id")
                        .todoId("todoId")
                        .title("title")
                        .status(false)
                        .build(),
                TodoItem.builder()
                        .id("id2")
                        .todoId("todoId")
                        .title("title2")
                        .status(true)
                        .build());
    }

    public static CreateToDoItemRequest getCreateToDoItemRequest(){
        return CreateToDoItemRequest.builder()
                .title("title")
                .build();
    }

    public static UpdateToDoItemRequest getUpdateToDoItemRequest(){
        return UpdateToDoItemRequest.builder()
                .id("id")
                .title("title update")
                .status(true)
                .build();
    }
}
