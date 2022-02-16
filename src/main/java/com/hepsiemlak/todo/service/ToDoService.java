package com.hepsiemlak.todo.service;

import com.hepsiemlak.todo.domain.dto.request.CreateToDoItemRequest;
import com.hepsiemlak.todo.domain.dto.request.CreateToDoRequest;
import com.hepsiemlak.todo.domain.dto.request.UpdateToDoItemRequest;
import com.hepsiemlak.todo.domain.dto.request.UpdateToDoRequest;
import com.hepsiemlak.todo.domain.dto.response.ToDoItemResponse;
import com.hepsiemlak.todo.domain.dto.response.ToDoResponse;
import com.hepsiemlak.todo.domain.model.Todo;
import com.hepsiemlak.todo.domain.model.TodoItem;
import com.hepsiemlak.todo.repository.ToDoItemRepository;
import com.hepsiemlak.todo.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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

    public ToDoResponse updateToDo(UpdateToDoRequest updateToDoRequest) {
        Todo todo = toDoRepository.findById(updateToDoRequest.getId()).orElse(null);

        if (todo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        todo.setTitle(updateToDoRequest.getTitle());

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

    public ToDoItemResponse updateToDoItem(String todoId, UpdateToDoItemRequest updateToDoItemRequest) {
        TodoItem todoItem = toDoItemRepository.findByTodoIdAndId(todoId, updateToDoItemRequest.getId()).orElse(null);

        if (todoItem == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        todoItem.setTitle(updateToDoItemRequest.getTitle());
        todoItem.setStatus(updateToDoItemRequest.getStatus());

        todoItem = toDoItemRepository.save(todoItem);

        return ToDoItemResponse.builder()
                .id(todoItem.getId())
                .todoId(todoItem.getTodoId())
                .title(todoItem.getTitle())
                .status(todoItem.getStatus())
                .build();
    }
}
