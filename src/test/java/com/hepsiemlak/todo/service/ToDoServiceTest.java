package com.hepsiemlak.todo.service;

import com.hepsiemlak.todo.domain.dto.request.CreateToDoItemRequest;
import com.hepsiemlak.todo.domain.dto.request.CreateToDoRequest;
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

import java.util.List;

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
}
