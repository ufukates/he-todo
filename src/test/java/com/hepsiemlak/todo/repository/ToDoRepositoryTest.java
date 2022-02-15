package com.hepsiemlak.todo.repository;

import com.hepsiemlak.todo.domain.model.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static com.hepsiemlak.todo.util.ToDoRepositoryUtil.getTodo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
public class ToDoRepositoryTest extends TestcontainerBase {

    @Autowired
    private ToDoRepository toDoRepository;

    @BeforeEach
    public void init() {
        toDoRepository = toDoRepository.withCollection("_default");
        toDoRepository.save(getTodo());
        toDoRepository.save(getTodo());
        toDoRepository.save(getTodo());
    }

    @Test
    public void should_createToDo() {
        System.out.println(couchbaseContainer.getContainerId() + " - " + couchbaseContainer.getConnectionString());

        //Given
        final Todo saveTodo = getTodo();
        toDoRepository.save(saveTodo);

        //When
        Todo actualResponse = toDoRepository.findById(saveTodo.getId()).get();

        //Then
        assertEquals(saveTodo.getId(), actualResponse.getId());
    }

    @Test
    public void should_getUserTodosWithUserId() {
        System.out.println(couchbaseContainer.getContainerId() + " - " + couchbaseContainer.getConnectionString());

        //Given
        final String userId = "userId";

        //When
        List<Todo> actualResponse = toDoRepository.getAllByUserId(userId);

        //Then
        assertTrue(actualResponse.size() > 0);

    }
}
