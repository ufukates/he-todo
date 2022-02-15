package com.hepsiemlak.todo.controller;

import com.hepsiemlak.todo.domain.dto.request.CreateToDoItemRequest;
import com.hepsiemlak.todo.domain.dto.request.CreateToDoRequest;
import com.hepsiemlak.todo.domain.dto.response.ToDoItemResponse;
import com.hepsiemlak.todo.domain.dto.response.ToDoResponse;
import com.hepsiemlak.todo.service.AppUserService;
import com.hepsiemlak.todo.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class ToDoController {

    private final ToDoService toDoService;
    private final AppUserService appUserService;

    @GetMapping
    public ResponseEntity<List<ToDoResponse>> todos() {
        List<ToDoResponse> todos = toDoService.getUserTodosWithUserId(appUserService.getUserId());
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<ToDoResponse> createToDo(@Valid @RequestBody CreateToDoRequest createToDoRequest) {
        ToDoResponse todo = toDoService.createToDo(appUserService.getUserId(), createToDoRequest);
        return ResponseEntity.ok(todo);
    }

    @GetMapping("/{todoId}/items")
    public ResponseEntity<List<ToDoItemResponse>> todoItems(@PathVariable("todoId") String todoId) {
        List<ToDoItemResponse> todoItems = toDoService.getTodoItemsWithTodoId(todoId);
        return ResponseEntity.ok(todoItems);
    }

    @PostMapping("/{todoId}/items")
    public ResponseEntity<ToDoItemResponse> createToDoItem(@PathVariable("todoId") String todoId, @Valid @RequestBody CreateToDoItemRequest createToDoItemRequest) {
        ToDoItemResponse todo = toDoService.createToDoItem(todoId, createToDoItemRequest);
        return ResponseEntity.ok(todo);
    }
}
