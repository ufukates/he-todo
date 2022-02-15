package com.hepsiemlak.todo.service;

import com.hepsiemlak.todo.domain.dto.request.CreateToDoItemRequest;
import com.hepsiemlak.todo.domain.dto.request.CreateToDoRequest;
import com.hepsiemlak.todo.domain.dto.response.ToDoItemResponse;
import com.hepsiemlak.todo.domain.dto.response.ToDoResponse;
import com.hepsiemlak.todo.domain.model.Todo;
import com.hepsiemlak.todo.domain.model.TodoItem;
import com.hepsiemlak.todo.repository.ToDoItemRepository;
import com.hepsiemlak.todo.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;
    private final ToDoItemRepository toDoItemRepository;

    public List<ToDoResponse> getUserTodosWithUserId(String userId) {
        List<ToDoResponse> list = new ArrayList<>();
        toDoRepository.getAllByUserId(userId)
                .forEach(todo -> list.add(ToDoResponse.builder()
                        .id(todo.getId())
                        .title(todo.getTitle())
                        .build()));
        return list;
    }

    public ToDoResponse createToDo(String userId, CreateToDoRequest createToDoRequest) {
        Todo todo = Todo.builder()
                .userId(userId)
                .title(createToDoRequest.getTitle())
                .build();
        todo = toDoRepository.save(todo);

        return ToDoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .build();
    }

    public List<ToDoItemResponse> getTodoItemsWithTodoId(String todoId) {
        List<ToDoItemResponse> list = new ArrayList<>();
        toDoItemRepository.getTodoItemsByTodoId(todoId)
                .forEach(todoItem -> list.add(ToDoItemResponse.builder()
                        .id(todoItem.getId())
                        .todoId(todoItem.getTodoId())
                        .title(todoItem.getTitle())
                        .status(todoItem.getStatus())
                        .build()));
        return list;
    }

    public ToDoItemResponse createToDoItem(String todoId, CreateToDoItemRequest createToDoItemRequest) {
        TodoItem todoItem = TodoItem.builder()
                .todoId(todoId)
                .title(createToDoItemRequest.getTitle())
                .status(false)
                .build();
        todoItem = toDoItemRepository.save(todoItem);

        return ToDoItemResponse.builder()
                .id(todoItem.getId())
                .todoId(todoItem.getTodoId())
                .title(todoItem.getTitle())
                .status(todoItem.getStatus())
                .build();
    }
}
