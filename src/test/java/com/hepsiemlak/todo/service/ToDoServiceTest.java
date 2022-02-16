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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static com.hepsiemlak.todo.util.ToDoServiceUtil.getCreateToDoItemRequest;
import static com.hepsiemlak.todo.util.ToDoServiceUtil.getCreateToDoRequest;
import static com.hepsiemlak.todo.util.ToDoServiceUtil.getToDoItemResponse;
import static com.hepsiemlak.todo.util.ToDoServiceUtil.getToDoItemsResponse;
import static com.hepsiemlak.todo.util.ToDoServiceUtil.getTodo;
import static com.hepsiemlak.todo.util.ToDoServiceUtil.getTodoItem;
import static com.hepsiemlak.todo.util.ToDoServiceUtil.getTodoItemWithId;
import static com.hepsiemlak.todo.util.ToDoServiceUtil.getTodoItems;
import static com.hepsiemlak.todo.util.ToDoServiceUtil.getTodoResponse;
import static com.hepsiemlak.todo.util.ToDoServiceUtil.getTodoWithId;
import static com.hepsiemlak.todo.util.ToDoServiceUtil.getTodos;
import static com.hepsiemlak.todo.util.ToDoServiceUtil.getTodosResponse;
import static com.hepsiemlak.todo.util.ToDoServiceUtil.getUpdateToDoItemRequest;
import static com.hepsiemlak.todo.util.ToDoServiceUtil.getUpdateToDoRequest;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ToDoServiceTest {

    private ToDoService toDoService;

    @Mock
    private ToDoRepository toDoRepository;

    @Mock
    private ToDoItemRepository toDoItemRepository;

    @BeforeEach
    public void setUp() {
        toDoService = new ToDoService(toDoRepository, toDoItemRepository);
    }

    @Test
    public void should_getUserTodosWithUserId() {
        //Given
        final String userId = "userId";
        final List<Todo> todos = getTodos();
        final List<ToDoResponse> todosResponse = getTodosResponse();

        //When
        Mockito.when(toDoRepository.getAllByUserId(userId)).thenReturn(todos);
        List<ToDoResponse> actualResponse = toDoService.getUserTodosWithUserId(userId);

        //Then
        Assertions.assertEquals(todosResponse, actualResponse);
    }

    @Test
    public void should_createToDo() {
        //Given
        final String userId = "userId";
        final Todo todo = getTodo();
        final Todo todoWithId = getTodoWithId();
        final ToDoResponse toDoResponse = getTodoResponse();
        final CreateToDoRequest createToDoRequest = getCreateToDoRequest();

        //When
        Mockito.when(toDoRepository.save(todo)).thenReturn(todoWithId);
        ToDoResponse actualResponse = toDoService.createToDo(userId, createToDoRequest);

        //Then
        Assertions.assertEquals(toDoResponse, actualResponse);
    }

    @Test
    public void should_updateToDo() {
        //Given
        final Todo todoWithId = getTodoWithId();
        todoWithId.setTitle("title update");
        final Optional<Todo> optionalTodoWithId = Optional.of(todoWithId);
        final ToDoResponse toDoResponse = getTodoResponse();
        final UpdateToDoRequest updateToDoRequest = getUpdateToDoRequest();
        toDoResponse.setTitle(updateToDoRequest.getTitle());

        //When
        Mockito.when(toDoRepository.findById(updateToDoRequest.getId())).thenReturn(optionalTodoWithId);
        Mockito.when(toDoRepository.save(todoWithId)).thenReturn(todoWithId);
        ToDoResponse actualResponse = toDoService.updateToDo(updateToDoRequest);

        //Then
        Assertions.assertEquals(toDoResponse.getId(), actualResponse.getId());
        Assertions.assertEquals(toDoResponse.getTitle(), actualResponse.getTitle());
    }

    @Test
    public void should_returnNotFound_updateToDo() {
        //Given
        final String message = "404 NOT_FOUND";
        final UpdateToDoRequest updateToDoRequest = getUpdateToDoRequest();

        //When
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            toDoService.updateToDo(updateToDoRequest);
        });

        //Then
        assertTrue(message.contains(exception.getMessage()));
    }

    @Test
    public void should_getTodoItemsWithTodoId() {
        //Given
        final String todoId = "todoId";
        final List<TodoItem> todoItems = getTodoItems();
        final List<ToDoItemResponse> toDoItemsResponse = getToDoItemsResponse();

        //When
        Mockito.when(toDoItemRepository.getTodoItemsByTodoId(todoId)).thenReturn(todoItems);
        List<ToDoItemResponse> actualResponse = toDoService.getTodoItemsWithTodoId(todoId);

        //Then
        Assertions.assertEquals(toDoItemsResponse, actualResponse);
    }

    @Test
    public void should_createToDoItem() {
        //Given
        final String todoId = "todoId";
        final TodoItem todoItem = getTodoItem();
        final TodoItem todoItemWithId = getTodoItemWithId();
        final ToDoItemResponse toDoItemResponse = getToDoItemResponse();
        final CreateToDoItemRequest createToDoItemRequest = getCreateToDoItemRequest();

        //When
        Mockito.when(toDoItemRepository.save(todoItem)).thenReturn(todoItemWithId);
        ToDoItemResponse actualResponse = toDoService.createToDoItem(todoId, createToDoItemRequest);

        //Then
        Assertions.assertEquals(toDoItemResponse, actualResponse);
    }

    @Test
    public void should_updateToDoItem() {
        //Given
        final String todoId = "todoId";
        final TodoItem todoItem = getTodoItem();
        final TodoItem todoItemWithId = getTodoItemWithId();
        final ToDoItemResponse toDoItemResponse = getToDoItemResponse();
        final UpdateToDoItemRequest updateToDoItemRequest = getUpdateToDoItemRequest();
        todoItem.setId(todoItemWithId.getId());
        todoItem.setTitle(updateToDoItemRequest.getTitle());
        todoItem.setStatus(updateToDoItemRequest.getStatus());
        todoItemWithId.setTitle(updateToDoItemRequest.getTitle());
        todoItemWithId.setStatus(updateToDoItemRequest.getStatus());
        toDoItemResponse.setTitle(updateToDoItemRequest.getTitle());
        toDoItemResponse.setStatus(updateToDoItemRequest.getStatus());
        final Optional<TodoItem> optionalTodoItemWithId = Optional.of(todoItemWithId);

        //When
        Mockito.when(toDoItemRepository.findByTodoIdAndId(todoId, updateToDoItemRequest.getId())).thenReturn(optionalTodoItemWithId);
        Mockito.when(toDoItemRepository.save(todoItem)).thenReturn(todoItemWithId);
        ToDoItemResponse actualResponse = toDoService.updateToDoItem(todoId, updateToDoItemRequest);

        //Then
        Assertions.assertEquals(toDoItemResponse, actualResponse);
    }

    @Test
    public void should_returnNotFound_updateToDoItem() {
        //Given
        final String message = "404 NOT_FOUND";
        final String todoId = "todoId";
        final UpdateToDoItemRequest updateToDoItemRequest = getUpdateToDoItemRequest();

        //When
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            toDoService.updateToDoItem(todoId, updateToDoItemRequest);
        });

        //Then
        assertTrue(message.contains(exception.getMessage()));
    }
}
