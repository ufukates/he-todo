package com.hepsiemlak.todo.repository;

import com.couchbase.client.java.query.QueryScanConsistency;
import com.hepsiemlak.todo.domain.model.Todo;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.DynamicProxyable;
import org.springframework.data.couchbase.repository.ScanConsistency;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("todoRepository")
@Collection("todos")
@ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
public interface ToDoRepository extends CouchbaseRepository<Todo, String>, DynamicProxyable<ToDoRepository> {

    List<Todo> getAllByUserId(String userId);
}
