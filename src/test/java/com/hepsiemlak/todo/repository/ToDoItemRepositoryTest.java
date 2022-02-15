package com.hepsiemlak.todo.repository;

import com.hepsiemlak.todo.domain.model.TodoItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static com.hepsiemlak.todo.util.ToDoItemRepositoryUtil.getTodoItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
public class ToDoItemRepositoryTest extends TestcontainerBase {

    @Autowired
    private ToDoItemRepository toDoItemRepository;

    @BeforeEach
    public void init() {
        toDoItemRepository = toDoItemRepository.withCollection("_default");
        toDoItemRepository.save(getTodoItem());
        toDoItemRepository.save(getTodoItem());
        toDoItemRepository.save(getTodoItem());
    }

    @Test
    public void should_createToDoItem() {
        System.out.println(couchbaseContainer.getContainerId() + " - " + couchbaseContainer.getConnectionString());

        //Given
        final TodoItem todoItem = getTodoItem();
        toDoItemRepository.save(todoItem);

        //When
        TodoItem actualResponse = toDoItemRepository.findById(todoItem.getId()).get();

        //Then
        assertEquals(todoItem.getId(), actualResponse.getId());
    }

    @Test
    public void should_getTodoItemsWithTodoId() {
        System.out.println(couchbaseContainer.getContainerId() + " - " + couchbaseContainer.getConnectionString());

        //Given
        final String todoId = "todoId";

        //When
        List<TodoItem> actualResponse = toDoItemRepository.getTodoItemsByTodoId(todoId);

        //Then
        assertTrue(actualResponse.size() > 0);
    }

}
