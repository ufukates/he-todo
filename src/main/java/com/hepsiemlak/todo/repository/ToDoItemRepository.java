package com.hepsiemlak.todo.repository;

import com.couchbase.client.java.query.QueryScanConsistency;
import com.hepsiemlak.todo.domain.model.TodoItem;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.DynamicProxyable;
import org.springframework.data.couchbase.repository.ScanConsistency;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("todoItemRepository")
@Collection("todoItems")
@ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
public interface ToDoItemRepository extends CouchbaseRepository<TodoItem, String>, DynamicProxyable<ToDoItemRepository> {

    List<TodoItem> getTodoItemsByTodoId(String todoId);
}
